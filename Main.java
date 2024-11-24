package recommendationsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FitnessPlan {
    // Private attributes restrict direct access, enforcing Least Privilege.
    private String name;
    private int minDuration; // in minutes
    private String requiredLevel;
    private String healthGoal;

    public FitnessPlan(String name, int minDuration, String requiredLevel, String healthGoal) {
        this.name = name;
        this.minDuration = minDuration;
        this.requiredLevel = requiredLevel;
        this.healthGoal = healthGoal;
    }

    // Getters provide controlled access to attributes, following Least Privilege.
    String getName() {
        return name;
    }

    int getMinDuration() {
        return minDuration;
    }

    String getRequiredLevel() {
        return requiredLevel;
    }
    
    

    String getHealthGoal() {
        return healthGoal;
    }
}

class User {
    // Private attributes protect sensitive user information, adhering to Least
    // Privilege.
    private String fitnessGoal;
    private String currentFitnessLevel;
    private int age;
   private List<String> medicalHistory;

    public User(String fitnessGoal, String currentFitnessLevel, int age, List<String> medicalHistory) {
        this.fitnessGoal = fitnessGoal;
        this.currentFitnessLevel = currentFitnessLevel;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    // Getters allow controlled access, maintaining the Least Privilege principle.
    String getFitnessGoal() {
        return fitnessGoal;
    }

    String getCurrentFitnessLevel() {
        return currentFitnessLevel;
    }

    int getAge() {
        return age;
    }

   List<String> getMedicalHistory() {
        return medicalHistory;
    }
   
   
    // Centralized validation choke point for user creation
    public static boolean validateUser(User user) {
        
        boolean Validage=user.getAge()>0;
         // cheak user age is bigger than 0
         
         
                 //cheak if the goal and level is null or emplty 
        boolean VFitGoal= user.getFitnessGoal()!=null && !user.getFitnessGoal().isEmpty();
        boolean VFitLevel =user.getCurrentFitnessLevel()!=null && !user.getCurrentFitnessLevel().isEmpty();
        
        if (!Validage){
            System.out.println("Age must be greater than 0 !");
        }
        if (!VFitGoal){
            System.out.println("Fitness goal can not be null or empty !");
        }
        
         if (!VFitLevel){
            System.out.println("Fitness level can not be null or empty !");
        }
        
        
        
        
        return VFitLevel && Validage && VFitGoal;
               
    }
    
}

// Changed to package-private (no public modifier)
class FitnessPlanRecommender {
    // Private list prevents unauthorized access and modifications, enforcing Least
    // Privilege.
    private static final List<FitnessPlan> fitnessPlans = new ArrayList<>();

    static {
        // Initial fitness plans are added securely, limiting future modifications.
        
        fitnessPlans.add(new FitnessPlan("Cardio", 150, "Beginner", "Weight Loss"));

        fitnessPlans.add(new FitnessPlan("Strength Training", 120, "Intermediate", "Muscle Building"));

        fitnessPlans.add(new FitnessPlan("Flexibility", 90, "Beginner", "Improve Flexibility"));

        fitnessPlans.add(new FitnessPlan("Yoga", 120, "Beginner", " Stress Relief"));
       
        fitnessPlans.add(new FitnessPlan("HIIT", 90, "Advanced", "Improve Cardiovascular Health"));

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner
        String fitnessGoal = null;
        String fitnessLevel = null;
        int age = 0;
        List<String>medicalHistory= new ArrayList<>();
        boolean run = true;

        
        
        while (run) {
            // Fitness Goals Selection
            while (fitnessGoal == null) {
                System.out.println("Select your fitness goal:");
                System.out.println("1. Weight Loss");
                System.out.println("2. Muscle Building");
                System.out.println("3. Improve Flexibility");
                System.out.println("4. Relief");
                System.out.println("5. HIIT");

                System.out.println("6. Exit");
                System.out.print("Enter the number corresponding to your goal: ");

                try {
                    int goalSelection = Integer.parseInt(scanner.nextLine());
                    if (goalSelection < 1 || goalSelection > 6) {
                        System.out.println("Invalid selection. choose a number from 1 to 6");
                    } else {
                        fitnessGoal = convertGoalToString(goalSelection);
                        if (goalSelection == 6) {
                            run = false;
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Fitness Levels Selection
            while (fitnessLevel == null) {
                
                System.out.println("\n Select your current fitness level:");
                System.out.println("1. Beginner");
                System.out.println("2. Intermediate");
                System.out.println("3. Advanced");
                System.out.println("4. Exit");
                System.out.print("Enter the number corresponding to your fitness level: ");

                try {
                    int levelSelection = Integer.parseInt(scanner.nextLine());
                    if (levelSelection < 1 || levelSelection > 4) {
                        System.out.println("Invalid selection. Please enter a number from 1 to 4.");
                    } else {
                        fitnessLevel = convertLevelToString(levelSelection);
                        if (levelSelection == 4) {
                            run = false;
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Age Input
            while (age <= 0) {
                System.out.print("Enter your age: ");
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    if (age <= 0) {
                        System.out.println("Invalid entery. Please enter a positive number greater than zero.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid age.");
                }
            }

            
            
            // Medical History Input            
            
            
            medicalHistory.clear(); // Clear previous selections if running in a loop
            
            
            System.out.println("\nSelect your medical history (choose multiple if applicable, type 'done' to finish):");
            System.out.println("1. Heart Condition");
            System.out.println("2. Asthma");
            System.out.println("3. Recent Surgery");
            System.out.println("4. Joint Problems");
            System.out.println("5. None");
            System.out.print("Enter the number corresponding to your condition (type 'done' when finished): ");

            boolean selectingMedicalHistory = true;
            while (selectingMedicalHistory) {
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1" -> medicalHistory.add("Heart Condition");
                    case "2" -> medicalHistory.add("Asthma");
                    case "3" -> medicalHistory.add("Recent Surgery");
                    case "4" -> medicalHistory.add("Joint Problems");
                    case "5" -> {
                        medicalHistory.add("None");
                        selectingMedicalHistory = false; // Stop further selections if user selects 'None'
                    }
                    case "done" -> selectingMedicalHistory = false;
                    default -> System.out.println("Invalid selection. Please enter a valid number or 'done'.");
                }
                
                
           
        }
            
            
            
                           
            
            
            
            
            
            
            
            
            
            
            
            
            
            // Create User instance
            User user = new User(fitnessGoal, fitnessLevel, age, medicalHistory);

            // Apply choke point validation before proceeding
            if (!User.validateUser(user)) {
                System.out.println("Invalid user data. Please check your inputs.");
                continue; // Skip to the next loop iteration if validation fails
            }

            List<FitnessPlan> recommendedPlans = recommendFitnessPlans(user);

            if (recommendedPlans.isEmpty()) {
                System.out.println("\nNo suitable fitness plans found for your selected goal: " + user.getFitnessGoal()
                        + ". Please try different options.");
            } else {
                System.out.println("\nSuggested Fitness Plans:");
                for (FitnessPlan plan : recommendedPlans) {
                    int totalRequiredTime = calculateRequiredExerciseTime(plan, user.getCurrentFitnessLevel());
                    System.out.println(
                            "- " + plan.getName() + " (Required Weekly Time: " + totalRequiredTime + " minutes)");
                }
            }

            System.out.println("\nAdditional Notes:");
            provideAdditionalNotes(user);

            // Reset inputs for next run if desired
            
            fitnessGoal = null;
            fitnessLevel = null;
            age = 0;
            medicalHistory.clear();
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }

    // Converts user input for fitness goals to strings. Keeping it private
    // limits access, adhering to Least Privilege.
    private static String convertGoalToString(int selection) {
        switch (selection) {
            case 1:
                return "Weight Loss";
            case 2:
                return "Muscle Building";
            case 3:
                return "Improve Flexibility";
            case 4:
                return "Relief";
            default:
                return "Unknown Goal";
        }
    }

    // Converts user input for fitness levels to strings. Keeping it private
    // prevents external modifications, following Least Privilege.
    private static String convertLevelToString(int selection) {
        switch (selection) {
            case 1:
                return "Beginner";
            case 2:
                return "Intermediate";
            case 3:
                return "Advanced";
            default:
                return "Unknown Level";
        }
    }

    // Recommends fitness plans based on user input, keeping the logic
    // private to enforce Least Privilege by limiting access.
    private static List<FitnessPlan> recommendFitnessPlans(User user) {
        List<FitnessPlan> recommendations = new ArrayList<>();

        for (FitnessPlan plan : fitnessPlans) {
            if (plan.getHealthGoal().equals(user.getFitnessGoal()) &&
                    user.getCurrentFitnessLevel().equals(plan.getRequiredLevel()) &&
                    calculateRequiredExerciseTime(plan, user.getCurrentFitnessLevel()) <= 180) { // max 180 minutes
                recommendations.add(plan);
            }
        }
        return recommendations;
    }

    // Calculates required exercise time based on fitness level, kept
    // private to prevent unauthorized access, aligning with Least Privilege.
    private static int calculateRequiredExerciseTime(FitnessPlan plan, String fitnessLevel) {
        int minDuration = plan.getMinDuration();
        int additionalMinutes = getAdditionalMinutes(fitnessLevel);
        
        return minDuration + additionalMinutes;
    }

    // Determines additional minutes based on fitness levels. Keeping it
    // private prevents external changes, maintaining Least Privilege.
    private static int getAdditionalMinutes(String fitnessLevel) {
        return switch (fitnessLevel) {
            case "Beginner" -> 30;
            case "Intermediate" -> 20;
            case "Advanced" -> 10;
            default -> 0;
        };
    }

    // Provides additional notes based on user attributes. Keeping it
    // private limits access, adhering to the Least Privilege principle.
    private static void provideAdditionalNotes(User user) {
        System.out.println("User Age: " + user.getAge());
        
        if (!user.getMedicalHistory().isEmpty()) {
            System.out.println("Medical History: " + user.getMedicalHistory());
        } else {
            System.out.println("No relevant medical history provided.");
        }
        
        if
        (user.getMedicalHistory().contains("Heart Condition")) {
            System.out.println("Note: Avoid intense workouts like HIIT due to heart condition.");
        }
        if (user.getMedicalHistory().contains("Asthma")) {
            System.out.println("Note: Avoid intense cardio and HIIT to prevent asthma triggers.");
        }
        if (user.getMedicalHistory().contains("Recent Surgery")) {
            System.out.println("Note: Focus on low-impact exercises for recovery post-surgery.");
        }
        if (user.getMedicalHistory().contains("Joint Problems")) {
            System.out.println("Note: Avoid high-impact or weight-bearing exercises due to joint issues.");
        }
        
        
    }
}
