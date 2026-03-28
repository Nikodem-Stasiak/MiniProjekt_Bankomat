public class KontoBankowe {
    private int saldo;

    public KontoBankowe(int saldoPodane){
        this.saldo = saldoPodane;
    }

    public  int getSaldo(){
        return this.saldo;
    }

    public void wplac(int kwota){
        this.saldo += kwota;
        System.out.println("Wpłacono: " + kwota + ". Stan konta obecnie wynosi: " + this.saldo);
    }

    public void wyplac(int kwota){
        if(kwota>this.saldo){
            throw new IllegalArgumentException("Bład! Kwota którą chcesz wypłacić przekracza saldo na twoim koncie!");
        } else {
            this.saldo -= kwota;
            System.out.println("Wypłacono: " + kwota + ". Stan konta obecnie wynosi: " + this.saldo);
        }
    }
}
