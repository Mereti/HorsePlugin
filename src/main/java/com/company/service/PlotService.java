package com.company.service;

import com.company.Gamer;
import com.company.Plot;
import com.company.repository.PlotRepository;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlotService {

    private static final int X = 23;

    private List<Plot> plots;
    private PlotRepository plotRepository;

    public PlotService(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
        this.plots = loadAllPlots();
    }

    public Optional<Plot>  getPlotById(int id){
        return plots.stream().filter(plot -> plot.getId()==id).findAny();
    }

    public Optional<Plot> getPlotByLocation(Location location){
        return  plots.stream().filter(plot -> {
            Location center = plot.getMainPoint();
            return Math.abs(center.getBlockX() - location.getBlockX()) <= X &&  Math.abs(center.getBlockZ() - location.getBlockZ()) <= X;
        }).findAny();
    }

    public List<Plot> loadAllPlots(){
        return plotRepository.loadAllPlots();
    }

    public Optional<Plot> getPlotOwnedByGamer(Gamer gamer){
        return plots.stream().filter(plot -> plot.getGamerId() == gamer.getGamerId()).findAny();
    }

    public Optional<Plot> givePlot(Gamer gamer){
        Optional<Plot> emptyPlotOptional = plots.stream().filter(plot-> !plot.hasOwner()).findFirst();
        if(emptyPlotOptional.isPresent()) {
            Plot plot = emptyPlotOptional.get();
            plot.setGamerId(gamer.getGamerId());
            plotRepository.updateOwner(plot.getId(), gamer.getGamerId());
            return Optional.of(plot);
        } else return Optional.empty();
    }

}
