package facebook;

/**
 * Created by lfrodrigues on 19/06/18.
 */
public class Population {

    private static class Person {

        int birth;
        int death;

        public Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }

        public boolean isAlive(int year) {
            return this.birth <= year && year <= this.death;
        }

    }

    public static void main(String[] args) {

        Person[] people = new Person[]{
                new Person(1981, 2000),
                new Person(1920, 1981),
                new Person(1950, 1990)
        };

        int firstYear = Integer.MAX_VALUE;
        int lastYear = Integer.MIN_VALUE;

        for(int i=0; i<people.length; i++) {

            Person p = people[i];
            if(p.birth < firstYear) {
                firstYear = p.birth;
            }

            if(p.death > lastYear) {
                lastYear = p.death;
            }
        }

        System.out.println("First year " + firstYear);
        System.out.println("Last year " + lastYear);

        int yearMaxPopulation = firstYear;
        int maxPopulation = 0;

        for(int year = firstYear; year<=lastYear ; year++) {

            int population = 0;
            for(int j=0; j<people.length; j++){
                if(people[j].isAlive(year)){
                    population++;
                }
            }

            if(population > maxPopulation) {
                maxPopulation = population;
                yearMaxPopulation = year;
            }
        }

        System.out.println("Max Population Year " + yearMaxPopulation);



    }

}
