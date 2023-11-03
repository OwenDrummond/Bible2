
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
public class driver
{
  public static void main(String [] args)
  {
    // Initial parameteres
      long totalPop = 7700000000L;
      long christianPop = 13L;
      long apprenticeCount;
      int year = 0;

    //Creating array lists for sepreate groups and filling them with our starting groups with defined parameters of human group.
      ArrayList<HumanGroup> disciples = new ArrayList<HumanGroup>();
      HumanGroup initialDiscs = new HumanGroup();
      initialDiscs.age = 30;
      initialDiscs.population = 13;
      initialDiscs.isDisc = true;
      disciples.add(initialDiscs);
      
      ArrayList<HumanGroup> noFaith = new ArrayList<HumanGroup>();
      HumanGroup remainingPeople = new HumanGroup();
      remainingPeople.age = 18;
      remainingPeople.population = totalPop - christianPop;
      noFaith.add(remainingPeople);
      
      ArrayList<HumanGroup> apprentices = new ArrayList<HumanGroup>();
      
      while(totalPop > christianPop && !noFaith.isEmpty() && !disciples.isEmpty())
      {
        // Calling death function for each array list
          death(disciples);
          death(noFaith);
          death(apprentices);

        // Calculating new babies based on birth function for each group
          long totalBaby = birth(disciples);
          totalBaby += birth(noFaith);
          totalBaby += birth(apprentices);
          totalBaby = totalBaby/2;
          
          if(totalBaby > 0)
          {
              HumanGroup baby = new HumanGroup();
              baby.population = totalBaby;
              //baby.age = 0;
              noFaith.add(baby);
            // Adds babies to non faithful group at birth
          }
          apprenticeCount = 0;
          for(HumanGroup g: disciples)
          {
              if(g.yearsTaught == 0)
              {
                // If the disciples have nobody they are currently teaching they're ready to teach new people.
                  apprenticeCount += g.population * Human.appPerProcess;
              }
          }
            
          Collections.sort(noFaith, HumanGroup.ageComparator);  
        // We want to train younger people first.
            
          Iterator<HumanGroup> noFaithIterator = noFaith.iterator();
          while(noFaithIterator.hasNext() && apprenticeCount >0)
          {
              HumanGroup current = noFaithIterator.next();
              HumanGroup newApprentice = new HumanGroup();
              if(current.age >= Human.trainAge)
              {
                // If the non faithfuls are old enough to be trained they are cloned. 
                  newApprentice = current.Clone();
                  newApprentice.isApp = true;
              }
              if(current.population <= apprenticeCount)
              {
                // The proper amount is then removed from the non-faithful group and put in the apprentice group.
                  noFaithIterator.remove();
                  apprenticeCount -= current.population;
              }
              else
              {
                  current.population -= apprenticeCount;
                  newApprentice.population = apprenticeCount;
                  apprenticeCount = 0;
              }
              apprentices.add(newApprentice);
          }
          
          for(HumanGroup g: disciples)
          {
              g.Aging();
              if(g.yearsTaught >= Human.trainingYears)
              {
                // We age disciples and check if they are old enough to teach.
                  g.yearsTaught = 0;
              }
          }
          Iterator<HumanGroup> appIterator = apprentices.iterator();
          while(appIterator.hasNext())
          {
              HumanGroup current = appIterator.next();
              current.Aging();
              if(current.yearsApp >= Human.trainingYears)
              {
                  current.yearsApp = 0;
                  current.isApp = false;
                  current.isDisc = true;
                  appIterator.remove();
                  disciples.add(current);
                // We age the apprentices and see if they are able to become disciples.
              }
          }
          for(HumanGroup g: noFaith)
          {
              g.Aging();
            // We age the non-faithful
          }
          totalPop = pop(disciples,noFaith,apprentices);
          christianPop = onePop(disciples);
        // Functions used to determine the population of all three groups and only disciples.
          year++;
          System.out.println("Year " + (2023 + year) + ": Christian population is: " + christianPop + ", and total population is "
                    + totalPop);
      }
      System.out.println(year);
  }
  
  public static void death(ArrayList<HumanGroup> group)
  {
    // function checks people of all groups to see if they are old enough to die
      Iterator<HumanGroup> iterator = group.iterator();
      while(iterator.hasNext())
      {
          HumanGroup current = iterator.next();
          if(current.age >= Human.deathAge)
          {
              iterator.remove();
          }
      }
  }
  public static long birth(ArrayList<HumanGroup> group)
  {
      long totalBaby = 0;
      for(HumanGroup g: group)
      {
        // for all groups, babies are made based on amount of people old enough to reproduce
          if(g.age == Human.birthAge)
          {
              totalBaby += g.population;
          }
      }
      return totalBaby;
  }
  
  public static long pop(ArrayList<HumanGroup> a, ArrayList<HumanGroup> b, ArrayList<HumanGroup> c)
  {
      long currentPop = 0;
      for(HumanGroup h: a)
      {
          currentPop += h.population;
      }
      for(HumanGroup h: b)
      {
          currentPop += h.population;
      }
      for(HumanGroup h: c)
      {
          currentPop += h.population;
      }
      return currentPop;
  }
  public static long onePop(ArrayList<HumanGroup> a)
  {
      long one = 0;
      for(HumanGroup h: a)
      {
          one += h.population;
      }
      return one;
  }
}
