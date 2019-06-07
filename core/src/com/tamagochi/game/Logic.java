package com.tamagochi.game;

public class Logic {
    public static final int AGE_HATCH = 12800;
    public static final int AGE_MATURE = 79600;
    public static final int AGE_DEATHFROMNATURALCAUSES = 819200;

    public static final int HUNGER_CANEAT = 80;
    public static final int HUNGER_NEEDSTOEAT = 35;
    public static final int HUNGER_SICKFROMNOTEATING = 15;
    public static final int HUNGER_DEADFROMNOTEATING = 0;

    private int Age;
    private int Hunger;
    private int Expunge;
    private int Thirst;
    private int Sleep;
    private int Happiness;

    public Logic(int hunger, int expunge,int thrist, int sleep, int happiness,int age, int time){
        int n=(int)(System.currentTimeMillis()-time)/(360000*20);
        setHunger(hunger-n);
        setExpunge(expunge-n);
        setThrist(thrist-n);
        setSleep(sleep-n);
        setHappiness(happiness);
        if(getHunger() < 0) setHunger(0);
        if(getExpunge() < 0) setExpunge(0);
        if(getThrist() < 0) setThrist(0);
        if(getSleep() < 0) setSleep(0);
        if(getHunger() > 100) setHunger(100);
        if(getExpunge() > 100) setExpunge(100);
        if(getThrist() > 100) setThrist(100);
        if(getSleep() > 100) setSleep(100);
        if(getHappiness() > 100) setHappiness(100);
        if(getExpunge()<HUNGER_NEEDSTOEAT) { setHappiness(happiness - 3 * (HUNGER_NEEDSTOEAT-getExpunge()));
        if(getHappiness() < 0) setHappiness(0); }
        setAge(age+n);
    }

    public void Cycle() {
        setExpunge(getExpunge()-1);
        setHunger(getHunger()-1);
        setThrist(getThrist()-1);
        setSleep(getSleep()-1);
        Age+=1;
        if(getExpunge()<= 15) setHappiness(getHappiness()-3);
        if(getHunger()<= 15) setHappiness(getHappiness()-1);
        if(getThrist()<= 15) setHappiness(getHappiness()-1);
        if(getHunger()>=50 && getThrist()>=50 || getExpunge()>=50) setHappiness(getHappiness()+2);
        if (getSleep()<0)setSleep(0);
        if (getHunger()<0)setHunger(0);
        if (getThrist()<0)setThrist(0);
        if (getExpunge()<0)setExpunge(0);
        if (getHappiness()<0)setHappiness(0);
        if (getHappiness()>100)setHappiness(100);
    }

    public boolean isDead(){
        if((getHunger()==0) || (getThrist()==0) || (getHappiness()==0) || (Age==AGE_DEATHFROMNATURALCAUSES)) return true;
        else return false;
    }

    public boolean isSleep(){
        if(getSleep()==0) return true;
        else return false;
    }

    public void doSleep(int sleep){
        setSleep(getSleep()+ sleep);
        if (getSleep()>100)setSleep(100);
    }
    public void doEat(int eat){
        setHunger(getHunger()+ eat);
        if (getHunger()>100) setHunger(100);
    }
    public void doDrink(int drink){
        setThrist(getThrist()+ drink);
        if (getThrist()>100) setThrist(100);
    }
    public void doWaste(){
        setExpunge(getExpunge()+ 80);
        if (getExpunge()>100) setExpunge(100);
    }
    public void doHappiness(int happiness){
        setHappiness(getHappiness()+ happiness);
        if (getHappiness()>100) setHappiness(100);
    }

    public void setHunger(int hunger){
        this.Hunger=hunger;
    }
    public void setExpunge(int expunge){
        this.Expunge=expunge;
    }
    public void setThrist(int thirst){
        this.Thirst=thirst;
    }
    public void setSleep(int sleep){
        this.Sleep=sleep;
    }
    public void setHappiness(int happiness){
        this.Happiness=happiness;
    }
    public void setAge(int age){this.Age=age;}
    public int getHunger(){ return this.Hunger; }
    public int getExpunge(){
        return this.Expunge;
    }
    public int getThrist(){
        return this.Thirst;
    }
    public int getSleep(){
        return this.Sleep;
    }
    public int getHappiness(){
        return this.Happiness;
    }
    public int getAge(){return this.Age;}
}
