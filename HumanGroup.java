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
        // initial parameters for human group upon creation
        this.population = 0;
        this.age = 0;
        this.isDisc = false;
        this.isApp = false;
        this.yearsTaught = 0;
        this.yearsApp = 0;
    }
    
    
    public HumanGroup Clone()
    {
        // creates a dummy human group based on a previously existing human group
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
        // ages everyone in group by one, tracks years as apprentice or years teaching
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
// sorts ages in order to train youngest first
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
