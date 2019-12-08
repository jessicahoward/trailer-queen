package org.launchcode.trailerqueen.models;

import org.json.JSONObject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private String latitude;

    private String longitude;

    private int code;

    private int sand;

    private int hardPack;

    private int jumps;

    private int largeRocks;

    private  int mud;

    private int hillClimb;

    private boolean motorcycle;

    private boolean atv;

    private boolean jeep;

    private boolean sxs;

    private int levelBeginner;

    private int levelAdvanced;

    private int levelIntermediate;

    private int levelExpert;

    public Park() {
    }

    public Park(String name, String description, String latitude, String longitude, int code, int sand, int hardPack, int jumps, int largeRocks, int mud, int hillClimb, boolean motorcycle, boolean atv, boolean jeep, boolean sxs, int levelBeginner, int levelAdvanced, int levelIntermediate, int levelExpert) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.code = code;
        this.sand = sand;
        this.hardPack = hardPack;
        this.jumps = jumps;
        this.largeRocks = largeRocks;
        this.mud = mud;
        this.hillClimb = hillClimb;
        this.motorcycle = motorcycle;
        this.atv = atv;
        this.jeep = jeep;
        this.sxs = sxs;
        this.levelBeginner = levelBeginner;
        this.levelAdvanced = levelAdvanced;
        this.levelIntermediate = levelIntermediate;
        this.levelExpert = levelExpert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSand() {
        return sand;
    }

    public void setSand(int sand) {
        this.sand = sand;
    }

    public int getHardPack() {
        return hardPack;
    }

    public void setHardPack(int hardPack) {
        this.hardPack = hardPack;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getLargeRocks() {
        return largeRocks;
    }

    public void setLargeRocks(int largeRocks) {
        this.largeRocks = largeRocks;
    }

    public int getMud() {
        return mud;
    }

    public void setMud(int mud) {
        this.mud = mud;
    }

    public int getHillClimb() {
        return hillClimb;
    }

    public void setHillClimb(int hillClimb) {
        this.hillClimb = hillClimb;
    }

    public boolean isMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(boolean motorcycle) {
        this.motorcycle = motorcycle;
    }

    public boolean isAtv() {
        return atv;
    }

    public void setAtv(boolean atv) {
        this.atv = atv;
    }

    public boolean isJeep() {
        return jeep;
    }

    public void setJeep(boolean jeep) {
        this.jeep = jeep;
    }

    public boolean isSxs() {
        return sxs;
    }

    public void setSxs(boolean sxs) {
        this.sxs = sxs;
    }

    public int getLevelBeginner() {
        return levelBeginner;
    }

    public void setLevelBeginner(int levelBeginner) {
        this.levelBeginner = levelBeginner;
    }

    public int getLevelAdvanced() {
        return levelAdvanced;
    }

    public void setLevelAdvanced(int levelAdvanced) {
        this.levelAdvanced = levelAdvanced;
    }

    public int getLevelIntermediate() {
        return levelIntermediate;
    }

    public void setLevelIntermediate(int levelIntermediate) {
        this.levelIntermediate = levelIntermediate;
    }

    public int getLevelExpert() {
        return levelExpert;
    }

    public void setLevelExpert(int levelExpert) {
        this.levelExpert = levelExpert;
    }
}
