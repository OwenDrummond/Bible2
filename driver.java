
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
public class driver
{
  public static void main(String [] args)
  {
      long totalPop = 7700000000L;
      long christianPop = 13L;
      long apprenticeCount;
      int year = 0;
      
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
          death(disciples);
          death(noFaith);
          death(apprentices);
          
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
          }
          apprenticeCount = 0;
          for(HumanGroup g: disciples)
          {
              if(g.yearsTaught == 0)
              {
                  apprenticeCount += g.population * Human.appPerProcess;
              }
          }
            
          Collections.sort(noFaith, HumanGroup.ageComparator);  
            
          Iterator<HumanGroup> noFaithIterator = noFaith.iterator();
          while(noFaithIterator.hasNext() && apprenticeCount >0)
          {
              HumanGroup current = noFaithIterator.next();
              HumanGroup newApprentice = new HumanGroup();
              if(current.age >= Human.trainAge)
              {
                  newApprentice = current.Clone();
                  newApprentice.isApp = true;
              }
              if(current.population <= apprenticeCount)
              {
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
              }
          }
          for(HumanGroup g: noFaith)
          {
              g.Aging();
          }
          totalPop = pop(disciples,noFaith,apprentices);
          christianPop = onePop(disciples);
          year++;
          System.out.println("Year " + (2023 + year) + ": Christian population is: " + christianPop + ", and total population is "
                    + totalPop);
      }
      System.out.println(year);
  }
  
  public static void death(ArrayList<HumanGroup> group)
  {
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
