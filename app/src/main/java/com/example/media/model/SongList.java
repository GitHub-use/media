package com.example.media.model;


import java.util.List;

public class SongList{

    /**
     * song : [{"bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","weight":"1662099","songname":"丑八怪","resource_type":"0","songid":"87967607","has_mv":"0","yyr_artist":"0","resource_type_ext":"2","artistname":"薛之谦","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"59081DD4093A085DD4D837"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"43099","songname":"丑八怪+演员","resource_type":"0","songid":"524442825","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"薛之谦","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"15081F4251E9085888A664"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"2099","songname":"丑八怪 (原唱:薛之谦)","resource_type":"0","songid":"293279119","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"JC练习生11队","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"3408117b158f0958622be4L"}]
     * order : song,album
     * error_code : 22000
     * album : [{"albumname":"丑八怪","weight":"0","artistname":"魏佳艺","resource_type_ext":"0","artistpic":"http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40","albumid":"611644689"},{"albumname":"丑八怪","weight":"0","artistname":"Mario","resource_type_ext":"0","artistpic":"http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40","albumid":"611655993"}]
     */

    private String order;
    private int error_code;
    private List<SongBean> song;
    private List<AlbumBean> album;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public static class SongBean {
        /**
         * bitrate_fee : {"0":"129|-1","1":"-1|-1"}
         * weight : 1662099
         * songname : 丑八怪
         * resource_type : 0
         * songid : 87967607
         * has_mv : 0
         * yyr_artist : 0
         * resource_type_ext : 2
         * artistname : 薛之谦
         * info :
         * resource_provider : 1
         * control : 0000000000
         * encrypted_songid : 59081DD4093A085DD4D837
         */

        private String bitrate_fee;
        private String weight;
        private String songname;
        private String resource_type;
        private String songid;
        private String has_mv;
        private String yyr_artist;
        private String resource_type_ext;
        private String artistname;
        private String info;
        private String resource_provider;
        private String control;
        private String encrypted_songid;

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getResource_type() {
            return resource_type;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getResource_provider() {
            return resource_provider;
        }

        public void setResource_provider(String resource_provider) {
            this.resource_provider = resource_provider;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }
    }

    public static class AlbumBean {
        /**
         * albumname : 丑八怪
         * weight : 0
         * artistname : 魏佳艺
         * resource_type_ext : 0
         * artistpic : http://qukufile2.qianqian.com/data2/pic/default_album.jpg@s_2,w_40,h_40
         * albumid : 611644689
         */

        private String albumname;
        private String weight;
        private String artistname;
        private String resource_type_ext;
        private String artistpic;
        private String albumid;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }
    }
}