package com.github.pepe79.tsgenerator.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class GenerationContext
{
	private Set<String> generatedTypeNames = new HashSet<String>();

	private Map<String, ReferenceEntry> externalTypes = new HashMap<String, ReferenceEntry>();

	public void addExternalType(String first, String secondary)
	{
		ReferenceEntry entry = externalTypes.get(first);
		if (entry == null)
		{
			entry = new ReferenceEntry();
			entry.setEntry(first);
			entry.setSecondaries(new HashSet<String>());
			externalTypes.put(entry.getEntry(), entry);
		}
		if (secondary != null)
		{
			entry.getSecondaries().add(secondary);
		}
	}

	public void addGeneratedType(String generatedType)
	{
		generatedTypeNames.add(generatedType);
	}

	private List<String> create(List<ReferenceEntry> entries)
	{
		List<String> result = new ArrayList<String>();
		for (ReferenceEntry e : entries)
		{
			if (generatedTypeNames.contains(e.getEntry()))
			{
				result.add(e.getEntry());
			}
		}
		return result;
	}

	public List<String> getExternalTypes()
	{
		return create(sort(new ArrayList<ReferenceEntry>(externalTypes.values())));
	}

	private void move(List<ReferenceEntry> list, int source, int dest)
	{
		dest = dest + 1;
		ReferenceEntry e = list.get(source);
		list.add(dest, e);
		if (dest < source)
		{
			source++;
		}
		list.remove(source);
	}

	private List<ReferenceEntry> sort(List<ReferenceEntry> entries)
	{
		boolean moveNeeded = true;
		while (moveNeeded)
		{
			moveNeeded = false;
			for (int c = entries.size() - 1; c >= 0; c--)
			{
				for (int i = entries.size() - 1; i > c; i--)
				{
					ReferenceEntry e = entries.get(c);
					ReferenceEntry e2 = entries.get(i);

					if (e2.getSecondaries().contains(e.getEntry()))
					{
						moveNeeded = true;
						move(entries, c, i);
						break;
					}
				}
				if (moveNeeded)
				{
					break;
				}
			}
		}

		return entries;
	}
}
