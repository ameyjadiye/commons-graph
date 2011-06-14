package org.apache.commons.graph.shortestpath;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static java.util.Collections.unmodifiableList;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.graph.Vertex;
import org.apache.commons.graph.WeightedEdge;
import org.apache.commons.graph.WeightedPath;

/**
 * Support {@link WeightedPath} implementation, optimized for algorithms (such Dijkstra's) that need to rebuild the path
 * traversing the predecessor list bottom-up.
 *
 * @param <V> the Graph vertices type
 * @param <WE> the Graph weighted edges type
 */
final class InMemoryPath<V extends Vertex, WE extends WeightedEdge<V>>
    implements WeightedPath<V, WE>
{

    private final V source;

    private final V target;

    private final Double weigth;

    private final LinkedList<V> vertices = new LinkedList<V>();

    private final LinkedList<WE> edges = new LinkedList<WE>();

    public InMemoryPath( V start, V end, Double weigth )
    {
        this.source = start;
        this.target = end;
        this.weigth = weigth;
    }

    /**
     * {@inheritDoc}
     */
    public V getSource()
    {
        return source;
    }

    /**
     * {@inheritDoc}
     */
    public V getTarget()
    {
        return target;
    }

    public void addVertexInHead( V vertex )
    {
        vertices.addFirst( vertex );
    }

    public void addVertexInTail( V vertex )
    {
        vertices.addLast( vertex );
    }

    /**
     * {@inheritDoc}
     */
    public List<V> getVertices()
    {
        return unmodifiableList( vertices );
    }

    public void addEdgeInHead( WE edge )
    {
        edges.addFirst( edge );
    }

    public void addEdgeInTail( WE edge )
    {
        edges.addLast( edge );
    }

    /**
     * {@inheritDoc}
     */
    public List<WE> getEdges()
    {
        return unmodifiableList( edges );
    }

    /**
     * {@inheritDoc}
     */
    public int size()
    {
        return vertices.size();
    }

    /**
     * {@inheritDoc}
     */
    public Double getWeight()
    {
        return weigth;
    }

}