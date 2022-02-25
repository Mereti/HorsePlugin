package com.company.service;

import com.company.Gamer;
import com.company.Plot;
import com.company.repository.PlotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: usunac wszystkie abstract
public class PlotService {

    private List<Plot> plots;
    private PlotRepository plotRepository;

    //TODO: napisz konstruktor ktory wywola loadAllPlots i ustawi plots na new ArrayList();


    public PlotService() {
    }

    public PlotService(List<Plot> plots) {
        loadAllPlots();
        this.plots = new ArrayList<>(plots);
    }


    public Optional<Plot>  getPlotById(int id){
        return plots.stream().filter(plot -> plot.getId()==id).findAny();
    }

    //TODO: znajd dzialke w obrebie ktorej znajduje sie dana lokacja np tak:
    // plots.stream().filter(plot -> plot.getCenterPoint().distance(location) < X).findAny()
    // gdzie X to rozmiar dzialki
    /*public Optional<Plot> getPlotByLocation(Location location){
      //  plots.stream().filter(plot -> plot.getMainPoint().distance(location)< ).findAny();
        return  plots.stream().filter(plot -> plot.getMainPoint().distance(location)< ).findAny();
    }*/


    //TODO: uzywajac plotRepository zaladuj z bazy danych wszystkie dzialki
    // plotRepository.loadAllPlots(); pamietaj zeby uzyc tej metody w konstruktorze
    public void loadAllPlots(){
        plotRepository.loadAllPlots();
    }

    //TODO: znajdz dzialke ktorej wlascicielem jest player
    // plots.stream().filter(plot -> plot.getGamerId() == gamer.getId()).findAny()
    public Optional<Plot> getPlotOwnedByGamer(Gamer gamer){
        return plots.stream().filter(plot -> plot.getGamerId() == gamer.getGamerId()).findAny();
    }

    //TODO: znajdz dzialke bez wlasciciela i daj ja gamerowi
    // pamietaj zeby zapisac zmiane w bazie danych updateOwner()
    // jesli nie ma wolnej dzilaki zwroc Optional.empty()
    public Optional<Plot> givePlot(Gamer gamer){
        Optional<Plot> isEmptyPloat = plots.stream().filter(plot-> !plot.hasOwner()).findFirst();
        if(isEmptyPloat.isPresent()){
            plots.get(isEmptyPloat.get().getId()).setGamerId(gamer.getGamerId());
            return plots.stream().findAny();
        }else return Optional.empty();
    }

}
