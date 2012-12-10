/*
 * jMemorize - Learning made easy (and fun) - A Leitner flashcards tool
 * Copyright(C) 2004-2008 Riad Djemili and contributors
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 1, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package jmemorize.core.media;

public abstract class AbstractMedia
{
    private String  m_id;
    private boolean m_inRepository;
    
    public String getId()
    {
        return m_id;
    }
    
    public void setId(String id)
    {
        m_id = id;
    }
    
    public boolean isInRepository()
    {
        return m_inRepository;
    }
    
    public void setInRepository(boolean inRepository)
    {
        m_inRepository = inRepository;
    }
}
