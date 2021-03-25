import java.io.Serializable;

public class Record implements Serializable {
    int ID;
    String Name;
    String	Sex;
    int	Age;
    int	Height;
    int	 Weight;
    String	Team;
    String	NOC;
    String	Games;
    int	Year;
    String	Season;
    String	City;
    String	Sport;
    String	Event;
    String	Medal;

    public Record(int ID,
                  String name,
                  String sex,
                  int age,
                  int height,
                  int weight,
                  String team,
                  String NOC,
                  String games,
                  int year,
                  String season,
                  String city,
                  String sport,
                  String event,
                  String medal) {
        this.ID = ID;
        Name = name;
        Sex = sex;
        Age = age;
        Height = height;
        Weight = weight;
        Team = team;
        this.NOC = NOC;
        Games = games;
        Year = year;
        Season = season;
        City = city;
        Sport = sport;
        Event = event;
        Medal = medal;
    }

    @Override
    public String toString() {
        return "Record{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", Height=" + Height +
                ", Weight=" + Weight +
                ", Team='" + Team + '\'' +
                ", NOC='" + NOC + '\'' +
                ", Games='" + Games + '\'' +
                ", Year=" + Year +
                ", Season='" + Season + '\'' +
                ", City='" + City + '\'' +
                ", Sport='" + Sport + '\'' +
                ", Event='" + Event + '\'' +
                ", Medal='" + Medal + '\'' +
                '}';
    }
}
