package gym_workout_tracking;

import jakarta.persistence.*;
import java.util.*;

public class MemberDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gym");
    EntityManager em = emf.createEntityManager();

    public void addMember(Member member) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(member);

        et.commit();

        System.out.println("Member added successfully");
    }

    public void searchMember(int id) {

        Member member = em.find(Member.class, id);

        if(member == null) {
            System.out.println("Member not found");
            return;
        }

        System.out.println("ID: " + member.getId());
        System.out.println("Name: " + member.getName());
        System.out.println("Membership: " + member.getMembershipType());
        System.out.println("Workouts:");

        for(Workout w : member.getWorkouts()) {

            System.out.println(" Exercise: " + w.getExerciseName());
            System.out.println(" Duration: " + w.getDuration());
            System.out.println(" Date: " + w.getWorkoutDate());
        }
    }

    public void updateWorkoutDuration(int memberId, int workoutId, int newDuration) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Member member = em.find(Member.class, memberId);

        if(member != null) {

            for(Workout w : member.getWorkouts()) {

                if(w.getId() == workoutId) {
                    w.setDuration(newDuration);
                }
            }

            em.merge(member);

            System.out.println("Workout updated successfully");
        }

        et.commit();
    }

    public void deleteMember(int id) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Member member = em.find(Member.class, id);

        if(member != null) {

            em.remove(member);
            System.out.println("Member deleted successfully");
        }

        et.commit();
    }
}