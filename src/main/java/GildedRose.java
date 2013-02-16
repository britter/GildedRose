import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {

        System.out.println("OMGHAI!");

        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        printItemsOnDay();
        updateQuality();
    }

    public static void printItemsOnDay() {
        System.out.println(String.format("| %45s | %7s | %7s |", "Name", "Quality", "Sell In"));
        String format = "| %45s | %7d | %7d |";
        for (Item item : items) {
            System.out.println(String.format(format, item.getName(), item.getQuality(), item.getSellIn()));
        }
    }


    //
    // Update quality
    //

    public static void updateQuality() {
        for (Item item : items) {
            updateQualityOf(item);
        }
    }

    private static void updateQualityOf(Item item) {
        processQualityOf(item);
        processSellInOf(item);
        processOverdueOf(item);
    }

    private static void processQualityOf(Item item) {
        if (shouldIncreaseQuality(item)) {
            increaseQuality(item);
        } else {
            decreaseQuality(item);
        }
    }

    private static void processSellInOf(Item item) {
        if (shouldLowerSellInDate(item)) {
            decreaseSellIn(item);
        }
    }

    private static void processOverdueOf(Item item) {
        if (isOverdue(item)) {
            processQualityOf(item);
        }
    }


    //
    // Property manipulation
    //

    private static void increaseQuality(Item item) {
        item.setQuality(item.getQuality() + 1);
        if (isBackstagePasses(item)) {
            if (item.getSellIn() < 11) {
                if (hasSuboptimalQuality(item)) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
            if (item.getSellIn() < 6) {
                if (hasSuboptimalQuality(item)) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
            if (isOverdue(item)) {
                removeQuality(item);
            }
        }
    }

    private static void decreaseQuality(Item item) {
        if (shouldDecreaseQuality(item)) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private static void removeQuality(Item item) {
        item.setQuality(0);
    }

    private static void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }


    //
    // Property checks
    //

    private static boolean shouldDecreaseQuality(Item item) {
        return hasSomeQualityLeft(item) && !isSulfuras(item);
    }

    private static boolean shouldLowerSellInDate(Item item) {
        return !isSulfuras(item);
    }

    private static boolean shouldIncreaseQuality(Item item) {
        return hasSuboptimalQuality(item) && (isAgedBrie(item) || isBackstagePasses(item));
    }

    private static boolean isOverdue(Item item) {
        return item.getSellIn() < 0;
    }

    private static boolean hasSuboptimalQuality(Item item) {
        return item.getQuality() < 50;
    }

    private static boolean hasSomeQualityLeft(Item item) {
        return item.getQuality() > 0;
    }


    //
    // Special item checks
    //

    private static boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.getName());
    }

    private static boolean isBackstagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.getName());
    }

    private static boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.getName());
    }

}