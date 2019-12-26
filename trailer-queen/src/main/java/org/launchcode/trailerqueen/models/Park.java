package org.launchcode.trailerqueen.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trail_info")
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_id")
    private int id;

    @Column(name = "park_name")
    private String name;

    @Column(name = "park_desc")
    private String description;

    @Column(name = "park_lat")
    private String latitude;

    @Column(name = "park_lng")
    private String longitude;

    @Column(name = "park_code")
    private int code;

    @Column(name = "hazard_sand")
    private int sand;

    @Column(name = "hazard_hardpack")
    private int hardPack;

    @Column(name = "hazard_jumps")
    private int jumps;

    @Column(name = "hazard_largerocks")
    private int largeRocks;

    @Column(name = "hazard_mud")
    private  int mud;

    @Column(name = "hazard_hillclimb")
    private int hillClimb;

    @Column(name = "allow_cycle")
    private boolean motorcycle;

    @Column(name = "allow_atv")
    private boolean atv;

    @Column(name = "allow_jeep")
    private boolean jeep;

    @Column(name = "allow_sxs")
    private boolean sxs;

    @Column(name = "level_beginner")
    private int levelBeginner;

    @Column(name = "level_advanced")
    private int levelAdvanced;

    @Column(name = "level_intermediate")
    private int levelIntermediate;

    @Column(name = "level_expert")
    private int levelExpert;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name = "user_fav_park", joinColumns = @JoinColumn(name = "park_id"), inverseJoinColumns = @JoinColumn(name = "auth_user_id"))
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
