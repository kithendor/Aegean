public class Loan {

    private String name;
    private double value;

    public Loan(String n, double v)
    {
    	name = n;
    	value = v;
    }


    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
    public void setName(String n) {
        name = n;
    }

    public void getValue(double v) {
        value = v;
    }

}

public class Customer{
	private String name;
	private int id;

	ArrayList<Loan> daneia = new ArrayList<Loan>();
	ArrayList<Account> logariasmoi = new ArrayList<Account>();
	ArrayList<Card> kartes = new ArrayList<Card>();
	public Customer(String n,int i)
	{

		name = n;
		id = i;
		logariasmoi.add(n,i);
		logariasmoi.add(n,i);
		logariasmoi.add(n,i);

		kartes.add();
	}

	public void getLoan(String type, double value)
	{
		daneia.add(new Loan(type,value));
	}

	private bool prison()
	{
		boolean fulaki = false;
		for(int i : daneia.length)
		{
			if(daneia.get(i)>100000) fulaki= true; 
		}

		return fulaki;
	}
}

public class Account{
	private int id;
	private String type;
	private String cName;
	private int cId;

	public Account(String n, int i)
	{
		cName = n;
		cId = i;
		id = calculateid();
		type = calculateType();
	}

	private int calculateid()
	{
		return 0;
	}
	private String calculateType()
	{
		return "d";
	}

}

public class Card{
	private String name;
	private double Balance;

	public double upDateBalance(double c)
	{
		return 0;
	}
}
