public class TreasureChest<T extends Item> {
    private T value;

    public TreasureChest(T value){
        this.value = value;
    }
    public void getName(){
        System.out.println("This chest contains: " + value);
    }
}
