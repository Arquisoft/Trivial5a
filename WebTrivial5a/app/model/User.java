package model;

@Entity
public class User extends Model {
	@Id
	public Long id;

	@Required
	public String login;
	@Required
	public String password;
	
	public boolean admin;
	public int numberCorrectAnswer;
	public int numberWrongAnswer;
	
	public int posicion;


	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public static User findById(Long id) {
		return finder.byId(id);
	}

	public static List<User> all() {
		return finder.all();
	}

	public static void create(User user) {
		user.save();
	}

	public static void delete(Long id) {
		finder.ref(id).delete();
	}
	
	public static void deleteAll() {
		finder.all().clear();
	}
	
	public static Finder<Long, User> finder = new Finder(Long.class, User.class);