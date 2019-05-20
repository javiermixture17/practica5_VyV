package com.alexaitken.gildedrose;

import java.util.Objects;

class Inventory {

    final private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }


    public void updateQuality() {
        for (Item item: items) {
            if (!Objects.equals(item.getName(), "Aged Brie")
                    && !Objects.equals(item.getName(), "Backstage passes to a TAFKAL80ETC concert")) {
                if (item.getQuality() > 0) {
                    if (!Objects.equals(item.getName(), "Sulfuras, Hand of Ragnaros")) {
                        item.setQuality(item.getQuality() - 1);
                    }
                }
            } else {
                if (item.getQuality() < 50) {
                    item.setQuality(item.getQuality() + 1);

                    if (Objects.equals(item.getName(), "Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.getSellIn() < 11) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }

                        if (item.getSellIn() < 6) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!Objects.equals(item.getName(), "Sulfuras, Hand of Ragnaros")) {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0) {
                if (!Objects.equals(item.getName(), "Aged Brie")) {
                    if (!Objects.equals(item.getName(), "Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.getQuality() > 0) {
                            if (!Objects.equals(item.getName(), "Sulfuras, Hand of Ragnaros")) {
                                item.setQuality(item.getQuality() - 1);
                            }
                        }
                    } else {
                        item.setQuality(0);
                    }
                } else {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }
}
