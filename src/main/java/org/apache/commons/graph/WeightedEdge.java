package org.apache.commons.graph;

/*
 * Copyright 2001-2011 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * A WeightedEdge is an {@link Edge} where a number (weight) is assigned to represent, for example,
 * costs, lengths or capacities, etc. depending on the problem.
 *
 * @param <W> the {@link Edge} weight.
 */
public interface WeightedEdge<W extends Number>
    extends Edge
{

    /**
     * Gets the weight attribute of the WeightedEdge object
     *
     * @return the weight attribute of the WeightedEdge object
     */
    W getWeight();

}