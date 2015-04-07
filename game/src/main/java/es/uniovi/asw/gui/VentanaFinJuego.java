package es.uniovi.asw.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import es.uniovi.asw.bussines.Game;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Question;
import es.uniovi.asw.model.User;

public class VentanaFinJuego extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTable tableUsuarios;

	private Game juego;

	private JTable tablePreguntas;
	
	
	

	/**
	 * Crea la ventana de estadísticas de los jugadores
	 */
	
	public VentanaFinJuego(Game juego) {
		
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.juego = juego;
		setBounds(100, 100, 1109, 733);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1091, 685);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblEstadisticasUsuarios = new JLabel("Estadisticas Usuarios");
		lblEstadisticasUsuarios.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEstadisticasUsuarios.setBounds(34, 33, 320, 33);
		contentPanel.add(lblEstadisticasUsuarios);

		JLabel lblEstadsticasPreguntas = new JLabel("Estadisticas Preguntas");
		lblEstadsticasPreguntas.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEstadsticasPreguntas.setBounds(391, 33, 345, 50);
		contentPanel.add(lblEstadsticasPreguntas);

		JScrollPane scrollPaneUsers = new JScrollPane();
		scrollPaneUsers.setBounds(12, 91, 356, 436);
		contentPanel.add(scrollPaneUsers);

		try {
			rellenarFilasUsers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		scrollPaneUsers.setViewportView(tableUsuarios);
		JScrollPane scrollPanePreguntas = new JScrollPane();
		scrollPanePreguntas.setEnabled(false);
		scrollPanePreguntas.setBounds(401, 91, 678, 441);
		contentPanel.add(scrollPanePreguntas);
		try {
			rellenarFilasPreguntas();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		scrollPanePreguntas.setViewportView(tablePreguntas);
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(12, 545, 1091, 140);
		contentPanel.add(buttonPane);

		JButton volverButton = new JButton("Salir");
		volverButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		volverButton.setBounds(903, 86, 148, 41);
		volverButton.setActionCommand("OK");
		volverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		buttonPane.setLayout(null);
		buttonPane.add(volverButton);
		getRootPane().setDefaultButton(volverButton);

		JButton reiniciarButton = new JButton("Reiniciar partida");
		reiniciarButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		reiniciarButton.setBounds(714, 86, 162, 41);
		reiniciarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game juegonuevo = new Game();
				PantallaInicial pn = new PantallaInicial(juegonuevo);
				pn.setVisible(true);
				pn.setLocationRelativeTo(null);
				dispose();
			}
		});
		buttonPane.setLayout(null);
		buttonPane.add(reiniciarButton);

		JLabel lblNewLabel = new JLabel("¡La partida ha terminado! El ganador es: "
		+juego.getUsuarioActivo().getLogin());
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(135, 24, 828, 36);
		buttonPane.add(lblNewLabel);
	}

	/**
	 * Coge datos del Juego y lo muestra por pantalla
	 * 
	 * @param table_model
	 * @throws Exception
	 */
	public void rellenarFilasUsers() throws Exception {
		String column_names[] = { "Usuario", "Acertadas", "Falladas" };
		DefaultTableModel table_model = new DefaultTableModel(column_names,
				juego.showEstadisticsUser().size());
		table_model.setNumRows(0);
		for (int i = 0; i < table_model.getRowCount(); i++) {
			table_model.removeRow(i);
		}
		for (User u : juego.showEstadisticsUser()) {
			Object[] objetos = new Object[3];
			objetos[0] = u.getLogin();
			objetos[1] = u.getNumberCorrectAnswer();
			objetos[2] = u.getNumberWrongAnswer();
			table_model.addRow(objetos);
		}
		tableUsuarios = new JTable(table_model);
		tableUsuarios.setEnabled(false);
		tableUsuarios.setRowSelectionAllowed(false);
		tableUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	/**
	 * Rellena las filas de la lista con las preguntas de cada categoría
	 * (difícil y fácil)
	 * 
	 * @throws Exception
	 */
	public void rellenarFilasPreguntas() throws Exception {
		String column_names[] = { "Categoria", "Preg mas facil",
				"Preg mas dificil" };
		DefaultTableModel table_model = new DefaultTableModel(column_names,
				juego.showEstadisticsQuestion().size());
		table_model.setNumRows(0);
		for (int i = 0; i < table_model.getRowCount(); i++) {
			table_model.removeRow(i);
		}
		for (Category c : juego.showEstadisticsQuestion()) {
			Object[] objetos = new Object[3];
			objetos[0] = c.getName();
			objetos[1] = ((Question) c.showEstadisticsCategory().get(
					"preguntaFacil")).getQuery();
			objetos[2] = ((Question) c.showEstadisticsCategory().get(
					"preguntaDificil")).getQuery();
			table_model.addRow(objetos);
		}
		tablePreguntas = new JTable(table_model);
		tablePreguntas.setRowHeight(150);
		tablePreguntas.setEnabled(false);
		tablePreguntas.setRowSelectionAllowed(false);
		tablePreguntas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		int margin = 2;
		packColumn(tablePreguntas, 0, margin);
		packColumn(tablePreguntas, 1, margin);
		packColumn(tablePreguntas, 2, margin);
	}

	public void packColumns(JTable table, int margin) {
		for (int c = 0; c < table.getColumnCount(); c++) {
			packColumn(table, c, 2);
		}
	}

	public void packColumn(JTable table, int vColIndex, int margin) {
		DefaultTableColumnModel colModel = (DefaultTableColumnModel) table
				.getColumnModel();
		TableColumn col = colModel.getColumn(vColIndex);
		int width = 0; // Obtém a largura do cabeçalho da coluna
		TableCellRenderer renderer = col.getHeaderRenderer();
		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}
		Component comp = renderer.getTableCellRendererComponent(table,
				col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width;
		for (int r = 0; r < table.getRowCount(); r++) {
			renderer = table.getCellRenderer(r, vColIndex);
			comp = renderer.getTableCellRendererComponent(table,
					table.getValueAt(r, vColIndex), false, false, r, vColIndex);
			width = Math.max(width, comp.getPreferredSize().width);
		}
		width += 2 * margin; // Configura a largura
		col.setPreferredWidth(width);
	}
}
