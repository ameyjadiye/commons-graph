package org.apache.commons.graph.model;

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

import java.util.LinkedHashSet;

import org.apache.commons.graph.Edge;
import org.apache.commons.graph.Graph;
import org.apache.commons.graph.GraphException;
import org.apache.commons.graph.MutableGraph;
import org.apache.commons.graph.Vertex;
import org.apache.commons.graph.VertexPair;

/**
 * Basic abstract in-memory based of a simple mutable {@link Graph} implementation.
 *
 * @param <V> the Graph vertices type
 * @param <E> the Graph edges type
 */
public abstract class BaseMutableGraph<V extends Vertex, E extends Edge>
    extends BaseGraph<V, E>
    implements MutableGraph<V, E>
{

    private static final long serialVersionUID = 1549113549446254183L;

    /**
     * {@inheritDoc}
     */
    public final void addVertex( V v )
    {
        if ( v == null )
        {
            throw new GraphException( "Impossible to add a null Vertex to the Graph" );
        }

        if ( getAdjacencyList().containsKey( v ) )
        {
            throw new GraphException( "Vertex '%s' already present in the Graph", v );
        }

        getAdjacencyList().put( v, new LinkedHashSet<V>() );

        decorateAddVertex( v );
    }

    /**
     * @param v
     */
    protected abstract void decorateAddVertex( V v );

    /**
     * {@inheritDoc}
     */
    public final void removeVertex( V v )
    {
        if ( v == null )
        {
            throw new GraphException( "Impossible to remove a null Vertex from the Graph" );
        }

        if ( !getAdjacencyList().containsKey( v ) ){
            throw new GraphException( "Vertex '%s' not present in the Graph", v );
        }

        for ( V tail : getAdjacencyList().get( v ) )
        {
            getIndexedEdges().remove( new VertexPair<Vertex>( v, tail ) );
        }
        getAdjacencyList().remove( v );

        decorateRemoveVertex( v );
    }

    /**
     *
     *
     * @param v
     */
    protected abstract void decorateRemoveVertex( V v );

    /**
     * {@inheritDoc}
     */
    public void addEdge( V head, E e, V tail )
    {
        if ( head == null )
        {
            throw new GraphException( "Null head Vertex not admitted" );
        }
        if ( e == null )
        {
            throw new GraphException( "Impossible to add a null Edge in the Graph" );
        }
        if ( tail == null )
        {
            throw new GraphException( "Null tail Vertex not admitted" );
        }

        if ( !getAdjacencyList().containsKey( head ) )
        {
            throw new GraphException( "Head Vertex '%s' not present in the Graph", head );
        }
        if ( !getAdjacencyList().containsKey( tail ) )
        {
            throw new GraphException( "Tail Vertex '%s' not present in the Graph", tail );
        }

        if ( getEdge( head, tail ) != null )
        {
            throw new GraphException( "Edge %s is already present in the Graph", e );
        }

        getAllEdges().add( e );

        internalAddEdge( head, e, tail );

        decorateAddEdge( head, e, tail );
    }

    protected void internalAddEdge( V head, E e, V tail )
    {
        getAdjacencyList().get( head ).add( tail );

        VertexPair<V> vertexPair = new VertexPair<V>( head, tail );
        getIndexedEdges().put( vertexPair, e );

        if ( !getIndexedVertices().containsKey( e ) )
        {
            getIndexedVertices().put( e, vertexPair );
        }
    }

    /**
     *
     *
     * @param e
     */
    protected abstract void decorateAddEdge( V head, E e, V tail );

    /**
     * {@inheritDoc}
     */
    public final void removeEdge( E e )
    {
        if ( e == null )
        {
            throw new GraphException( "Impossible to add a null Edge in the Graph" );
        }

        // TODO to be completed

        decorateRemoveEdge( e );
    }

    /**
     *
     *
     * @param e
     */
    protected abstract void decorateRemoveEdge( E e );

}
