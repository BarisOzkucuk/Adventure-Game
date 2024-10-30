public abstract class BattleLoc extends Location {
  protected    Obstacle obstacle;
  protected  String award;

    BattleLoc(Player player,String name,Obstacle obstacle,String award ){
        super(player);
        this.obstacle = obstacle;
        this.name = name;
        this.award = award;

    }

    @Override
    public boolean getLocation() {
        int obsCount = obstacle.count();
        System.out.println("Şuan buradasınız: "+this.getName());
        System.out.println("Dikkatli ol ! Burada "+obsCount +" tane "+obstacle.getName()+" yaşıyor !");
        System.out.print("<S>avaş veya <K>aç: ");
        String selCase = scan.nextLine();
        selCase = selCase.toUpperCase();
        if(selCase.equals("S")){
            if (combat(obsCount)){
                System.out.println(this.getName()+" Bölgesindeki "+"tüm düşmanları temizlediniz !");
                if (this.award.equals("Food")&&player.getInv().isFood()== false){
                    System.out.println(this.award+" Kazandınız!");
                   player.getInv().setFood(true);
                }else if (this.award.equals("Water")&&player.getInv().isWater()== false){
                    System.out.println(this.award+" Kazandınız!");
                    player.getInv().setWater(true);
                }else if (this.award.equals("Firewood")&&player.getInv().isFirewood()== false){
                    System.out.println(this.award+" Kazandınız!");
                    player.getInv().setFirewood(true);
                }
                return true;
        }
            if (player.getHealty()<=0){
                System.out.println("Öldünüz");
                return false;
            }

            }

            return true;
    }
    public boolean combat(int obsCount){
        int defObsHealth = obstacle.getHealth();

            for (int i=0;i<obsCount;i++){
            playerStats();
            enemyStats();
            while(player.getHealty()>0 && obstacle.getHealth() >0){
                System.out.println("<V>ur veya <K>aç: ");
                String selCase = scan.nextLine();
                selCase = selCase.toUpperCase();
                if(selCase.equals("V")){
                    System.out.println("Siz vurdunuz !");
                    obstacle.setHealth(obstacle.getHealth()-player.getTotalDamage());
                    afterHit();
                    if(obstacle.getHealth()> 0){
                        System.out.println();
                        System.out.println("Canavar size vurdu ! ");
                        player.setHealty(player.getHealty()-(obstacle.getDamage()-player.getInv().getArmor()));
                        afterHit();
                    }

                }else{
                    return false;
                }

            }
            if(obstacle.getHealth() <=0 && player.getHealty()>0){
                System.out.println(obstacle.getName()+"adlı canavarı öldürdünüz");
                player.setMoney(player.getMoney()+obstacle.getAward());
                System.out.println("Güncel paranız "+player.getMoney());
                obstacle.setHealth(defObsHealth);
            }else {
                return false;
            }
                System.out.println("----------------------------------");
            }
            return true;
        }

        public void playerStats(){
            System.out.println("Oyuncu değerleri \n-------------");
            System.out.println("Can: "+player.getHealty());
            System.out.println("Hasar: "+player.getTotalDamage());
            System.out.println("Para: "+player.getMoney());
            if(player.getInv().getDamage()>0){
                System.out.println("Silah: "+player.getInv().getwName());
            }
            if(player.getInv().getArmor()>0){
                System.out.println("Silah: "+player.getInv().getaName());
            }

        }
        public void enemyStats(){
            System.out.println("\n"+obstacle.getName() +"Değerleri\n-------------");
            System.out.println("Can: "+obstacle.getHealth());
            System.out.println("Hasar: "+obstacle.getDamage());
            System.out.println("Ödül: "+obstacle.getAward());
        }
        public void afterHit(){
            System.out.println("Oyuncu canı: "+player.getHealty());
            System.out.println(obstacle.getName()+ "Canı: "+obstacle.getHealth());
        }
    }

