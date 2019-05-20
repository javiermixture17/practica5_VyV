package com.alexaitken.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;


public class InventoryTest {

    private Inventory createInventory(Item... items) {
        return new Inventory(items);
    }

    @Test //P3
    public void should_never_changes_quailty_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(80, sulfuras.getQuality());
    }

    @Test //P3
    public void should_never_changes_sellIn_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(0, sulfuras.getSellIn());
    }

    @Test //P9
    public void should_lower_the_sellIn_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(9, normalItem.getSellIn());
    }

    @Test //P9
    public void should_lower_the_quality_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(19, normalItem.getQuality());
    }

    @Test //P9b
    public void should_not_lower_the_quality_below_zero() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 0);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(0, normalItem.getQuality());
    }

    @Test //P10
    public void should_lower_the_quality_twice_as_fast_once_the_sell_in_date_has_passed() {
        Item normalItem = new Item("+5 Dexterity Vest", -1, 25);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(23, normalItem.getQuality());
    }

    @Test //P1
    public void should_increase_the_quality_of_aged_brie_as_it_gets_older() {
        Item agedBrie = new Item("Aged Brie", 10, 25);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(26, agedBrie.getQuality());
    }

    @Test //P1b
    public void should_not_increase_the_quality_of_aged_brie_over_50() {
        Item agedBrie = new Item("Aged Brie", 10, 50);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(50, agedBrie.getQuality());
    }

    @Test
    public void should_lower_backstage_passes_to_zero_quality_once_concert_has_happened() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(0, backStagePass.getQuality());
    }

    @Test //P4
    public void should_increase_backstage_passes_quality_by_1_when_the_concert_is_more_than_10_days_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(21, backStagePass.getQuality());
    }

    @Test //P5
    public void should_increase_backstage_passes_quality_by_2_when_the_concert_is_10_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(29, backStagePass.getQuality());
    }

    @Test //P6
    public void should_increase_backstage_passes_quality_by_3_when_the_concert_is_5_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(47, backStagePass.getQuality());
    }

    @Test //P4b //P5c //P6b //P5b  //P5e //P5f //P6b
    public void should_not_increase_backstage_passes_above_a_quality_of_50() {
        Item backStagePassMoreThan10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50);
        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        Item backStagePass5DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);
        Item backStagePass5DaysAway2 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        Item backStagePass10DaysAway2 = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50);
        Item backStagePass6DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50);
        Item backStagePass6DaysAway2 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50);

        Inventory inventory = createInventory(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway, backStagePass10DaysAway2, backStagePass6DaysAway, backStagePass6DaysAway2, backStagePass5DaysAway2);
        inventory.updateQuality();
        assertEquals(50, backStagePassMoreThan10DaysAway.getQuality());
        assertEquals(50, backStagePass10DaysAway.getQuality());
        assertEquals(50, backStagePass10DaysAway2.getQuality());
        assertEquals(50, backStagePass5DaysAway.getQuality());
        assertEquals(50, backStagePass6DaysAway.getQuality());
        assertEquals(50, backStagePass6DaysAway2.getQuality());
        assertEquals(50, backStagePass5DaysAway2.getQuality());
    }


    @Test //P2
    public void should_increase_the_qualit_of_aged_brie_twice_as_fast_once_the_sell_in_date_has_passed() {
        Item agedBrie = new Item("Aged Brie", -8, 25);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(27, agedBrie.getQuality());
    }


    @Test //P2b //P2c
    public void should_not_increase_aged_brie_above_a_quality_of_50_once_the_sell_in_date_has_passed() {
        Item agedBrie1 = new Item("Aged Brie", -8, 50);
        Item agedBrie2 = new Item("Aged Brie", -8, 49);
        Inventory inventory = createInventory(agedBrie1, agedBrie2);
        inventory.updateQuality();
        assertEquals(50, agedBrie1.getQuality());
        assertEquals(50, agedBrie2.getQuality());
    }

    @Test //P2d
    public void should_lower_the_sellIn_of_aged_brie_by_one__when_the_sell_in_date_is_0() {
        Item agedBrie = new Item("Aged Brie", 0, 25);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(-1, agedBrie.getSellIn());
    }

    @Test //P5d
    public void should_lower_the_sellIn_of_backstage_passes_by_one__when_the_sell_in_date_is_6() {
        Item backStagePass6DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 25);
        Inventory inventory = createInventory(backStagePass6DaysAway);
        inventory.updateQuality();
        assertEquals(5, backStagePass6DaysAway.getSellIn());
    }

    @Test   //P10d
    public void should_lower_twice_after_sellin(){
        Item dexteritySellinToday = new Item("Dexterity Vest", 0, 25);
        Inventory inventory = createInventory(dexteritySellinToday);
        inventory.updateQuality();
        assertEquals(23, dexteritySellinToday.getQuality());
        assertEquals(-1, dexteritySellinToday.getSellIn());
    }

    @Test   //P10c
    public void should_not_lower_after_zero(){
        Item dexterity = new Item("Dexterity Vest", -9, 0);
        Inventory inventory = createInventory(dexterity);
        inventory.updateQuality();
        assertEquals(0, dexterity.getQuality());
        assertEquals(-10, dexterity.getSellIn());
    }

    @Test   //P10b
    public void should_lower_one_before_zero(){
        Item dexterity = new Item("Dexterity Vest", -8, 1);
        Inventory inventory = createInventory(dexterity);
        inventory.updateQuality();
        assertEquals(0, dexterity.getQuality());
        assertEquals(-9, dexterity.getSellIn());
    }

    @Test   //P8
    public void should_backstage_not_lower_after_zero(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", -8, 1);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(0, passes.getQuality());
        assertEquals(-9, passes.getSellIn());
    }

    @Test   //P7
    public void should_backstage_lower_after_concert(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 25);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(0, passes.getQuality());
        assertEquals(-1, passes.getSellIn());
    }

    @Test   //P6h
    public void should_backstage_increase_before_concert_but_not_over_50(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 48);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(50, passes.getQuality());
        assertEquals(0, passes.getSellIn());
    }

    @Test   //P6g
    public void should_backstage_increase_before_concert_but_not_over_50_b(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(50, passes.getQuality());
        assertEquals(0, passes.getSellIn());
    }
    @Test   //P6f
    public void should_backstage_increase_before_concert_but_not_over_50_c(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(50, passes.getQuality());
        assertEquals(0, passes.getSellIn());
    }

    @Test   //P6e
    public void should_backstage_increase_by_three(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 25);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(28, passes.getQuality());
        assertEquals(0, passes.getSellIn());
    }
    @Test   //P6c
    public void should_backstage_increase_by_two_but_not_over_50(){
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Inventory inventory = createInventory(passes);
        inventory.updateQuality();
        assertEquals(50, passes.getQuality());
        assertEquals(4, passes.getSellIn());
    }

    @Test
    public void should_never_changes_quailty_of_Sulfuras_once_the_sell_in_date_has_passed() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(80, sulfuras.getQuality());
    }




}
