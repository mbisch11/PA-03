import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BinarySearchTreeTests {

    private BinarySearchTree bst;
    private Album album1; // 10 songs
    private Album album2; // 5 songs
    private Album album3; // 20 songs
    private Album album4; // 15 songs
    private Album album5; // 8 songs

    @Test
    public void testContains(){
        bst = new BinarySearchTree<>();

        ArrayList<String> artists1 = new ArrayList<>();
        ArrayList<String> artists2 = new ArrayList<>();
        ArrayList<String> artists3 = new ArrayList<>();
        ArrayList<String> artists4 = new ArrayList<>();
        ArrayList<String> artists5 = new ArrayList<>();

        artists1.add("Artist 1");
        artists2.add("Artist 2");
        artists3.add("Artist 3");
        artists4.add("Artist 4");
        artists5.add("Artist 5");

        album1 = new Album(1, artists1, "Album 1", 10);
        album2 = new Album(2, artists2, "Album 2", 5);
        album3 = new Album(3, artists3, "Album 3", 20);
        album4 = new Album(4, artists4, "Album 4", 15);
        album5 = new Album(5, artists5, "Album 5", 8);

        Assert.assertEquals(true ,bst.contains(album3));

        Album dummyAlbum = new Album(6, "Dummy", 100);
        Assert.assertEquals(false, bst.contains(dummyAlbum));
    }

    @Test
    public void testPartition() {
        bst = new BinarySearchTree<>();

        ArrayList<String> artists1 = new ArrayList<>();
        ArrayList<String> artists2 = new ArrayList<>();
        ArrayList<String> artists3 = new ArrayList<>();
        ArrayList<String> artists4 = new ArrayList<>();
        ArrayList<String> artists5 = new ArrayList<>();

        artists1.add("Artist 1");
        artists2.add("Artist 2");
        artists3.add("Artist 3");
        artists4.add("Artist 4");
        artists5.add("Artist 5");

        album1 = new Album(1, artists1, "Album 1", 10);
        album2 = new Album(2, artists2, "Album 2", 5);
        album3 = new Album(3, artists3, "Album 3", 20);
        album4 = new Album(4, artists4, "Album 4", 15);
        album5 = new Album(5, artists5, "Album 5", 8);

        Album thresholdAlbum = new Album(0, "dummy", 10);
        ArrayList<Album> partitioned = bst.partition(thresholdAlbum);

        Assert.assertEquals(3, partitioned.size());
        Assert.assertEquals(true, partitioned.contains(album1));
        Assert.assertEquals(true, partitioned.contains(album3));
        Assert.assertEquals(true, partitioned.contains(album4));
    }
}
