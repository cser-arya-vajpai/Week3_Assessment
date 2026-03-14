package gym_workout_tracking;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MemberDAO dao = new MemberDAO();

        System.out.println("Enter name membershipType exerciseName duration workoutDate");

        String name = sc.next();
        String membership = sc.next();
        String exercise = sc.next();
        int duration = sc.nextInt();
        String date = sc.next();

        Member member = new Member();
        member.setName(name);
        member.setMembershipType(membership);

        Workout workout = new Workout();
        workout.setExerciseName(exercise);
        workout.setDuration(duration);
        workout.setWorkoutDate(date);

        workout.setMember(member);

        List<Workout> list = new ArrayList<>();
        list.add(workout);

        member.setWorkouts(list);

        dao.addMember(member);

        dao.searchMember(member.getId());
    }
}