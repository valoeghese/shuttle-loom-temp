package net.fabricmc.loom.util.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import org.cadixdev.lorenz.model.MethodMapping;
import org.gradle.api.Project;

import net.fabricmc.loom.LoomGradleExtension;

public class ShuttleMojangMappingsDependency extends MojangMappingsDependency {
	public ShuttleMojangMappingsDependency(Project project, LoomGradleExtension extension) {
		super(project, extension);
	}

	@Override
	public String getGroup() {
		return "valoghese.shuttle";
	}

	@Override
	public String getName() {
		return "shuttle-mojmap";
	}

	@Override
	protected TinyWriter createTinyWriter(Writer writer, String namespaceFrom, String namespaceTo) {
		return new TinyWriterShuttle(writer, namespaceFrom, namespaceTo);
	}

	@Override
	protected void notifyMappings(BufferedReader clientBufferedReader) throws IOException {
		project.getLogger().warn("=======================================");
		project.getLogger().warn("         ShuttleLoom 1.0.0");
		project.getLogger().warn("Powered by Fabric and the Shuttle Project.");
		project.getLogger().warn("=======================================");
	}
	
	protected static class TinyWriterShuttle extends TinyWriter {
		protected TinyWriterShuttle(Writer writer, String namespaceFrom, String namespaceTo) {
			super(writer, namespaceFrom, namespaceTo);
		}

		@Override
		protected String deobfMethod(MethodMapping methodMapping) {
			String prefix = methodMapping.getObfuscatedName().startsWith("method") ? "mc_" : "";
			return prefix + super.deobfMethod(methodMapping);
		}
	}
}
