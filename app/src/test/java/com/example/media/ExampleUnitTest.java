package com.example.media;

import com.example.media.model.SongInfo;
import com.example.media.model.SongList;
import com.example.media.util.GetMusic;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.media.util.GetMusicLink;
import com.google.gson.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        System.out.println(GetMusicLink.GetSongLink("dance monkey"));

    }
}