package io.jenkins.plugins.forensics.blame;

import java.io.Serializable;

/**
 * Obtains SCM blame information for several file locations.
 *
 * @author Lukas Krose
 */
public abstract class Blamer implements Serializable {
    private static final long serialVersionUID = 1980235877389921937L;

    /**
     * Obtains author and commit information for the specified file locations.
     *
     * @param fileLocations
     *         the file locations to get the blames for
     *
     * @return the blames
     */
    public abstract Blames blame(FileLocations fileLocations);

    /**
     * A blamer that does nothing.
     */
    public static class NullBlamer extends Blamer {
        private static final long serialVersionUID = 6235885974889709821L;

        @Override
        public Blames blame(final FileLocations fileLocations) {
            return new Blames();
        }
    }
}
