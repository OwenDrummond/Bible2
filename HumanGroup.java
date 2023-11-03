import java.util.Comparator;
public class HumanGroup
{
    public long population;
    public int age;
    public boolean isDisc;
    public boolean isApp;
    public int yearsTaught;
    public int yearsApp;
    
    public HumanGroup()
    {
        this.population = 0;
        this.age = 0;
        this.isDisc = false;
        this.isApp = false;
        this.yearsTaught = 0;
        this.yearsApp = 0;
    }
    
    
    public HumanGroup Clone()
    {
        HumanGroup h = new HumanGroup();
        h.age = this.age;
        h.population = this.population;
        h.isDisc = this.isDisc;
        h.isApp = this.isApp;
        h.yearsTaught = this.yearsTaught;
        h.yearsApp = this.yearsApp;
        return h;
    }
    
    public void Aging()
    {
        this.age += 1;
        if(this.isApp)
        {
            this.yearsApp += 1;
        }
        if(this.isDisc)
        {
            this.yearsTaught += 1;
        }
    }
    
    public static Comparator<HumanGroup> ageComparator = new Comparator<HumanGroup>()
    {

        public int compare(HumanGroup o1, HumanGroup o2) 
        {
    			if (o1.age > o2.age) {
    				return 1;
    			} else if (o1.age == o2.age) {
    				return 0;
    			} else {
    				return -1;
    			}
        }
    };

    }
