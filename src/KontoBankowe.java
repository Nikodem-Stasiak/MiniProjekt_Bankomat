public class KontoBankowe {
    private int saldo;
    private String numerKonta;
    private int pin;

    public KontoBankowe(String numerKonta, int pin,int saldoPodane){
        this.numerKonta = numerKonta;
        this.pin = pin;
        this.saldo = saldoPodane;
    }

    public KontoBankowe(String numerKonta, int pin) {
        this.numerKonta = numerKonta;
        this.pin = pin;
        this.saldo = 0;
    }

    public String getNumerKonta(){
        return this.numerKonta;
    }

    public int getPin(){
        return  this.pin;
    }

    public  int getSaldo(){
        return this.saldo;
    }


    public void wplac(int kwota){
        this.saldo += kwota;
        System.out.println("Wpłacono: " + kwota);
    }

    public void wyplac(int kwota){
        if(kwota>this.saldo){
            throw new IllegalArgumentException("Bład! Kwota którą chcesz wypłacić przekracza saldo na twoim koncie!");
        } else {
            this.saldo -= kwota;
            System.out.println("Wypłacono: " + kwota);
        }
    }

    public void setSaldo(int noweSaldo) {
        this.saldo = noweSaldo;
    }
}
