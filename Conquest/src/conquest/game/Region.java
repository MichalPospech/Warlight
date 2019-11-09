// Copyright 2014 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//    
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.
package conquest.game;

import java.util.ArrayList;

import conquest.game.world.WorldContinent;
import conquest.game.world.WorldRegion;

public class Region {
    private WorldRegion worldRegion;
    private int id;
    private ArrayList<Region> neighbors;
    private Continent continent;
    private int armies;
    private int owner;
    
    public Region(WorldRegion region, int id, Continent continent)
    {
        this.worldRegion = region;
        this.id = id;
        this.continent = continent;
        this.neighbors = new ArrayList<Region>();
        this.owner = 0;
        this.armies = 0;
        if (continent != null) {
            continent.addRegion(this);
        }
    }
    
    public Region(WorldRegion region, int id, Continent continent, int owner, int armies)
    {
        this.worldRegion = region;
        this.id = id;
        this.continent = continent;
        this.neighbors = new ArrayList<Region>();
        this.owner = owner;
        this.armies = armies;
        
        continent.addRegion(this);
    }
    
    public void addNeighbor(Region neighbor)
    {
        if(!neighbors.contains(neighbor))
        {
            neighbors.add(neighbor);
            neighbor.addNeighbor(this);
        }
    }
    
    /**
     * @param region a Region object
     * @return True if this Region is a neighbor of given Region, false otherwise
     */
    public boolean isNeighbor(Region region)
    {
        return neighbors.contains(region);
    }

    /**
     * @param player
     * @return True if this region is owned by the given player, false otherwise
     */
    public boolean isOwnedBy(int player)
    {
        return owner == player;
    }
    
    /**
     * @param armies Sets the number of armies that are on this Region
     */
    public void setArmies(int armies) {
        this.armies = armies;
    }
    
    /**
     * @param playerName Sets the player that this Region belongs to
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }
    
    /**
     * @return The id of this Region
     */
    public int getId() {
        return id;
    }

    public String mapName() {
        return worldRegion.mapName;
    }
    
    /**
     * @return A list of this Region's neighboring Regions
     */
    public ArrayList<Region> getNeighbors() {
        return neighbors;
    }

    /**
     * @return The continent this Region is part of
     */
    public Continent getContinent() {
        return continent;
    }
    
    /**
     * @return The number of armies on this region
     */
    public int getArmies() {
        return armies;
    }
    
    /**
     * @return The player that owns this region
     */
    public int getOwner() {
        return owner;
    }
    
    public boolean isNeutral() {
        return owner == 0;
    }

    public WorldRegion getWorldRegion() {
        return worldRegion;
    }

    public WorldContinent getWorldContinent() {
        return worldRegion.worldContinent;
    }
    
    public boolean isVisible(int player) {
        if (isOwnedBy(player))
            return true;
        
        for (Region s : getNeighbors())
            if (s.isOwnedBy(player))
                return true;
        
        return false;
    }
    
    @Override
    public String toString() {
        return worldRegion.name() + "[" + owner + "|" + armies + "]";
    }
}