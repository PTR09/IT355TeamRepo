private void SEC01-J(final String file) throws FileNotFoundException {
  final String clean;
  try {
    clean = SanitizeNameAndPath(file);
  } catch (/* Break if an exception is thrown */) {
    //log exception
  }
  try {
    FileInputStream in =
        (FileInputStream) AccessController.doPrivileged(
          new PrivilegedExceptionAction() {
        public FileInputStream run() throws FileNotFoundException {
          return new FileInputStream(clean);
        }
      }
    );
    // Critical code
  } catch (PrivilegedActionException e) {
    // Forward to handler
  }
}