class Main {
  public static void main(String[] args) {
    Rubik cube = new Rubik();
    //cube.scramble(20);
    System.out.println(cube.solve());
    cube.print();
  }
}
