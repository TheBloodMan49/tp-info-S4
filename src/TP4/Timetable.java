package TP4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class containing the characteristics of a timetable.
 * 
 * @author kdesnos
 */
public class Timetable {
	static final int MAX_LESSONS = 100;
	static final int MAX_ROOMS = 30;
	static final int MAX_COURSES = 30;

	public static void main(String[] args) {
		// Test addCourse, addLesson, addRoom
		Timetable t = new Timetable();
		Course c1 = new Course(1, "Maths", "Dupont");
		Course c2 = new Course(2, "Physique", "Durand");
		Course c3 = new Course(3, "Chimie", "Martin");
		Room r1 = new Room(1, 30);
		Room r2 = new Room(2, 20);
		Room r3 = new Room(3, 10);
		Lesson l1 = new Lesson(8, 1, 1);
		Lesson l2 = new Lesson(10, 2, 2);
		Lesson l3 = new Lesson(14, 3, 3);

		t.addCourse(c1);
		t.addCourse(c2);
		t.addCourse(c3);
		t.addRoom(r1);
		t.addRoom(r2);
		t.addRoom(r3);
		try {
			t.addLesson(l1);
			t.addLesson(l2);
			t.addLesson(l3);
		} catch (UnknownException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		t.print();

		// Test parseFile
		Timetable t2 = new Timetable();
		t2.parseFile("assets/TP4/timetable.txt");
		t2.print();

		// Test exception handling in parseFile
		Timetable t3 = new Timetable();
		t3.parseFile("assets/TP4/doesnotexist.txt");

	}

	private Lesson[] scheduledLessons;
	private int nbScheduledLessons;

	private Room[] existingRooms;
	private int nbExistingRooms;

	private Course[] existingCourses;
	private int nbExistingCourses;

	public Timetable() {
		scheduledLessons = new Lesson[MAX_LESSONS];
		nbScheduledLessons = 0;
		existingRooms = new Room[MAX_ROOMS];
		nbExistingRooms = 0;
		existingCourses = new Course[MAX_COURSES];
		nbExistingCourses = 0;
	}

	private void addCourse(Course c) {
		if(nbExistingCourses >= MAX_COURSES)
			return;
		this.existingCourses[nbExistingCourses] = c;
		nbExistingCourses++;
	}


	private void addLesson(Lesson l) throws Exception {
		// Check if the room or course does not exist -> throw UnknownRoomException or UnknownCourseException
		if(getRoom(l.getRoomNumber()) == null)
			throw new UnknownRoomException("Room " + l.getRoomNumber() + " does not exist");
		if(getCourse(l.getCourseId()) == null)
			throw new UnknownCourseException("Course " + l.getCourseId() + " does not exist");

		// Check if the room is already used at this time -> throw OccupiedRoomException
		for (int i = 0; i < nbScheduledLessons; i++) {
			if(scheduledLessons[i].getRoomNumber() == l.getRoomNumber() &&
					scheduledLessons[i].getStartTime() == l.getStartTime())
				throw new OccupiedRoomException("Room " + l.getRoomNumber() +
						" is already used at time " + l.getStartTime());
		}

		if(nbScheduledLessons >= MAX_LESSONS)
			return;
		this.scheduledLessons[nbScheduledLessons] = l;
		nbScheduledLessons++;
	}

	private void addRoom(Room r) {
		if(nbExistingRooms >= MAX_ROOMS)
			return;
		this.existingRooms[nbExistingRooms] = r;
		nbExistingRooms++;
	}

	private Course getCourse(int id) {
		for (int i = 0; i < nbExistingCourses; i++) {
			if (existingCourses[i].getId() == id)
				return existingCourses[i];
		}
		return null;
	}

	private Room getRoom(int num) {
		for (int i = 0; i < nbExistingRooms; i++) {
			if (existingRooms[i].getNumber() == num)
				return existingRooms[i];
		}
		return null;
	}

	/**
	 * Parse the content of the {@link File} designated by the given path. <br>
	 * If the path is not valid display an error message and return false. In
	 * this method, we assume that the format of the parsed file is always
	 * correct. (i.e. do not verify it in your code !)
	 * 
	 * @param path
	 *            the path of the parsed {@link File}.
	 * @return <code>true</code> if the {@link File} was successfully opened and
	 *         its information were readable, <code>false</code> otherwise.
	 */
	public boolean parseFile(String path) {
		// each line contains the information as follows : <Type>,<FirstAttribute>,<SecondAttribute>,<ThirdAttribute>
		// Type can be Room, Course or Lesson

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			int lineCount = 0;
			while (br.ready()) {
				lineCount++;
				String line = br.readLine();

				String[] parts = line.split(",");
				if (parts[0].equals("Room")) {
					Room r = new Room(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					this.addRoom(r);
				} else if (parts[0].equals("Course")) {
					Course c = new Course(Integer.parseInt(parts[1]), parts[2], parts[3]);
					this.addCourse(c);
				} else if (parts[0].equals("Lesson")) {
					Lesson l = new Lesson(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
							Integer.parseInt(parts[3]));

					try {
						this.addLesson(l);
					} catch (UnknownException | OccupiedRoomException e) {
						if (e instanceof UnknownException) {
							System.out.println("Error in line " + lineCount + ": " + e.getMessage());
						} else {
							// Set the lesson in another room
							System.out.println("Trying to move lesson " + l.getCourseId() +
									" at time "+ l.getStartTime() +" to another room");
							for (int i = 0; i < nbExistingRooms; i++) {
								if (existingRooms[i].getNumber() != l.getRoomNumber()) {
									Lesson rebookedL = new Lesson(l.getCourseId(), existingRooms[i].getNumber(),
											l.getStartTime());

									try {
										this.addLesson(rebookedL);
										break;
									} catch (OccupiedRoomException | UnknownException e1) {
										if (e1 instanceof UnknownException)
											System.out.println("Error in line " + lineCount + ": " + e1.getMessage());
									} catch (Exception e1) {
										System.out.println("Something weird happened !\n" +
												"Error in line " + lineCount + ": " + e1.getMessage());
									}
								}
							}

							// If no room was found, print an error message
							System.out.println("Couldn't find a room for lesson " + l.getCourseId() +
									" at time " + l.getStartTime());
						}
					} catch (Exception e1) {
						System.out.println("Something weird happened !\n" +
								"Error in line " + lineCount + ": " + e1.getMessage());
					}
				}
			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void print() {
		System.out.println("Timetable");

		// Print list of Rooms
		System.out.print(nbExistingRooms + " Room(s): ");
		for (int i = 0; i < nbExistingRooms; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(existingRooms[i]);
		}
		System.out.println("");

		// Print list of Courses
		System.out.println(nbExistingCourses + " Course(s): ");
		for (int i = 0; i < nbExistingCourses; i++) {
			System.out.println("\t" + existingCourses[i]);
		}

		// Print the scheduled lessons
		System.out.println(nbScheduledLessons + " Lessons are scheduled:");
		for (int i = 0; i < nbScheduledLessons; i++) {
			System.out.println("\t" + scheduledLessons[i]);
		}
	}
}
