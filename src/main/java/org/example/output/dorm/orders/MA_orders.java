package org.example.output.dorm.orders;


import org.example.gma.templates.MATemplate;
import org.example.gma.templates.TableTemplate;

import org.example.output.dorm.orders.raw_bass.TAB_raw_bass;
import org.example.output.dorm.orders.raw_sp_video.TAB_raw_sp_video;
import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;
import org.example.output.dorm.orders.raw_addon_shell.TAB_raw_addon_shell;
import org.example.output.dorm.orders.fun_hub_queue.TAB_fun_hub_queue;
import org.example.output.dorm.orders.raw_asap.TAB_raw_asap;
import org.example.output.dorm.orders.raw_addon_frontier.TAB_raw_addon_frontier;
import org.example.output.dorm.orders.overwrite.TAB_overwrite;
import org.example.output.dorm.orders.raw_asap_voice.TAB_raw_asap_voice;
import org.example.output.dorm.orders.raw_metronet.TAB_raw_metronet;
import org.example.output.dorm.orders.raw_sp_mobility.TAB_raw_sp_mobility;
import org.example.output.dorm.orders.master_raw_addons.TAB_master_raw_addons;
import org.example.output.dorm.orders.raw_addon_metronet.TAB_raw_addon_metronet;
import org.example.output.dorm.orders.raw_vexus.TAB_raw_vexus;
import org.example.output.dorm.orders.raw_lumos_voice.TAB_raw_lumos_voice;
import org.example.output.dorm.orders.raw_lumos_video.TAB_raw_lumos_video;
import org.example.output.dorm.orders.raw_sp_internet.TAB_raw_sp_internet;
import org.example.output.dorm.orders.raw_bass_voice.TAB_raw_bass_voice;
import org.example.output.dorm.orders.master_orders.TAB_master_orders;
import org.example.output.dorm.orders.raw_lumos.TAB_raw_lumos;
import org.example.output.dorm.orders.raw_frontier.TAB_raw_frontier;
import org.example.output.dorm.orders.raw_addon_vexus.TAB_raw_addon_vexus;

import org.example.ContextInstance.KDBContext;

public class MA_orders extends MATemplate {

    public MA_orders() {
        super(
            "orders"
,
            "this is the db that holds data related to sales and orders placed by sellers from different carriers"
,
            new String[]{"sales", "carriers"},
            "ma2b679088-9cd0-4dd9-b2dd-87823bb0cabd"
,
            "dorm"

        );
    }


 KDBContext context = KDBContext.KDB_CONTEXT;

    private final TAB_raw_bass TAB_raw_bass = new TAB_raw_bass();
    public TAB_raw_bass getTAB_raw_bass() {
        return TAB_raw_bass;
    }


    private final TAB_raw_sp_video TAB_raw_sp_video = new TAB_raw_sp_video();
    public TAB_raw_sp_video getTAB_raw_sp_video() {
        return TAB_raw_sp_video;
    }


    private final TAB_master_raw_orders TAB_master_raw_orders = new TAB_master_raw_orders();
    public TAB_master_raw_orders getTAB_master_raw_orders() {
        return TAB_master_raw_orders;
    }


    private final TAB_raw_addon_shell TAB_raw_addon_shell = new TAB_raw_addon_shell();
    public TAB_raw_addon_shell getTAB_raw_addon_shell() {
        return TAB_raw_addon_shell;
    }


    private final TAB_fun_hub_queue TAB_fun_hub_queue = new TAB_fun_hub_queue();
    public TAB_fun_hub_queue getTAB_fun_hub_queue() {
        return TAB_fun_hub_queue;
    }


    private final TAB_raw_asap TAB_raw_asap = new TAB_raw_asap();
    public TAB_raw_asap getTAB_raw_asap() {
        return TAB_raw_asap;
    }


    private final TAB_raw_addon_frontier TAB_raw_addon_frontier = new TAB_raw_addon_frontier();
    public TAB_raw_addon_frontier getTAB_raw_addon_frontier() {
        return TAB_raw_addon_frontier;
    }


    private final TAB_overwrite TAB_overwrite = new TAB_overwrite();
    public TAB_overwrite getTAB_overwrite() {
        return TAB_overwrite;
    }


    private final TAB_raw_asap_voice TAB_raw_asap_voice = new TAB_raw_asap_voice();
    public TAB_raw_asap_voice getTAB_raw_asap_voice() {
        return TAB_raw_asap_voice;
    }


    private final TAB_raw_metronet TAB_raw_metronet = new TAB_raw_metronet();
    public TAB_raw_metronet getTAB_raw_metronet() {
        return TAB_raw_metronet;
    }


    private final TAB_raw_sp_mobility TAB_raw_sp_mobility = new TAB_raw_sp_mobility();
    public TAB_raw_sp_mobility getTAB_raw_sp_mobility() {
        return TAB_raw_sp_mobility;
    }


    private final TAB_master_raw_addons TAB_master_raw_addons = new TAB_master_raw_addons();
    public TAB_master_raw_addons getTAB_master_raw_addons() {
        return TAB_master_raw_addons;
    }


    private final TAB_raw_addon_metronet TAB_raw_addon_metronet = new TAB_raw_addon_metronet();
    public TAB_raw_addon_metronet getTAB_raw_addon_metronet() {
        return TAB_raw_addon_metronet;
    }


    private final TAB_raw_vexus TAB_raw_vexus = new TAB_raw_vexus();
    public TAB_raw_vexus getTAB_raw_vexus() {
        return TAB_raw_vexus;
    }


    private final TAB_raw_lumos_voice TAB_raw_lumos_voice = new TAB_raw_lumos_voice();
    public TAB_raw_lumos_voice getTAB_raw_lumos_voice() {
        return TAB_raw_lumos_voice;
    }


    private final TAB_raw_lumos_video TAB_raw_lumos_video = new TAB_raw_lumos_video();
    public TAB_raw_lumos_video getTAB_raw_lumos_video() {
        return TAB_raw_lumos_video;
    }


    private final TAB_raw_sp_internet TAB_raw_sp_internet = new TAB_raw_sp_internet();
    public TAB_raw_sp_internet getTAB_raw_sp_internet() {
        return TAB_raw_sp_internet;
    }


    private final TAB_raw_bass_voice TAB_raw_bass_voice = new TAB_raw_bass_voice();
    public TAB_raw_bass_voice getTAB_raw_bass_voice() {
        return TAB_raw_bass_voice;
    }


    private final TAB_master_orders TAB_master_orders = new TAB_master_orders();
    public TAB_master_orders getTAB_master_orders() {
        return TAB_master_orders;
    }


    private final TAB_raw_lumos TAB_raw_lumos = new TAB_raw_lumos();
    public TAB_raw_lumos getTAB_raw_lumos() {
        return TAB_raw_lumos;
    }


    private final TAB_raw_frontier TAB_raw_frontier = new TAB_raw_frontier();
    public TAB_raw_frontier getTAB_raw_frontier() {
        return TAB_raw_frontier;
    }


    private final TAB_raw_addon_vexus TAB_raw_addon_vexus = new TAB_raw_addon_vexus();
    public TAB_raw_addon_vexus getTAB_raw_addon_vexus() {
        return TAB_raw_addon_vexus;
    }


}