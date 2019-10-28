/**
 * @author Teddy Clarke
 */
public class Rubik {
  private char[][][] sides;

  public Rubik() {
    sides = new char[6][3][3];
    int count = 0;
    for(char[][] x : sides) {
      for(char[] y : x) {
        y[0] = color(count);
        y[1] = color(count);
        y[2] = color(count);
      }
      count++;
    }
  }

  public char[][][] getSides() {
    return sides;
  }

  public void print() {
    for(char[][] x : sides) {
      for(char[] y : x) {
        for (char z : y) {
          System.out.print(z);
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  public void print(int side) {
    for (char[] x : sides[side]) {
      for (char y : x) {
        System.out.print(y);
      }
      System.out.println();
    }
    System.out.println();
  }

  public char color(int side) {
    if (side == 0)
      return 'w';
    if (side == 1)
      return 'g';
    if (side == 2)
      return 'r';
    if (side == 3)
      return 'b';
    if (side == 4)
      return 'o';
    return 'y';
  }
  //turning section//
  public void turnR(int h) {
    char[] temp = sides[1][h];
    sides[1][h] = sides[4][h];
    sides[4][h] = sides[3][h];
    sides[3][h] = sides[2][h];
    sides[2][h] = temp;
  }

  public void turnL(int h) {
    char[] temp = sides[1][h];
    sides[1][h] = sides[2][h];
    sides[2][h] = sides[3][h];
    sides[3][h] = sides[4][h];
    sides[4][h] = temp;
  }

  public void turnDR(int h) {
    char[] left = {sides[4][2][h],sides[4][1][h],sides[4][0][h]};
    char[] right = {sides[2][2][2-h],sides[2][1][2-h],sides[2][0][2-h]};
    char[] top = sides[0][h];
    char[] bottom = sides[5][2-h];
    sides[0][h] = left;
    sides[5][2-h] = right;
    sides[4][0][h] = bottom[0];
    sides[4][1][h] = bottom[1];
    sides[4][2][h] = bottom[2];
    sides[2][0][2-h] = top[0];
    sides[2][1][2-h] = top[1];
    sides[2][2][2-h] = top[2];
  }

  public void turnDL(int h) {
    char[] left = {sides[4][0][h],sides[4][1][h],sides[4][2][h]};
    char[] right = {sides[2][0][2-h],sides[2][1][2-h],sides[2][2][2-h]};
    char[] top = sides[0][h];
    char[] bottom = sides[5][2-h];
    sides[0][h] = right;
    sides[5][2-h] = left;
    sides[4][0][h] = top[2];
    sides[4][1][h] = top[1];
    sides[4][2][h] = top[0];
    sides[2][0][2-h] = bottom[2];
    sides[2][1][2-h] = bottom[1];
    sides[2][2][2-h] = bottom[0];
  }

  public void turnU(int h) {
    char[] top = {sides[0][0][h],sides[0][1][h], sides[0][2][h]};
    char[] front = {sides[1][0][h],sides[1][1][h],sides[1][2][h]};
    char[] bottom = {sides[5][0][h],sides[5][1][h],sides[5][2][h]};
    char[] back = {sides[3][0][2-h],sides[3][1][2-h],sides[3][2][2-h]};
    sides[0][0][h] = front[0];
    sides[0][1][h] = front[1];
    sides[0][2][h] = front[2];
    sides[1][0][h] = bottom[0];
    sides[1][1][h] = bottom[1];
    sides[1][2][h] = bottom[2];
    sides[5][0][h] = back[2];
    sides[5][1][h] = back[1];
    sides[5][2][h] = back[0];
    sides[3][0][2-h] = top[2];
    sides[3][1][2-h] = top[1];
    sides[3][2][2-h] = top[0];
  }

  public void turnD(int h) {
    char[] top = {sides[0][0][h],sides[0][1][h], sides[0][2][h]};
    char[] front = {sides[1][0][h],sides[1][1][h],sides[1][2][h]};
    char[] bottom = {sides[5][0][h],sides[5][1][h],sides[5][2][h]};
    char[] back = {sides[3][0][2-h],sides[3][1][2-h],sides[3][2][2-h]};
    sides[0][0][h] = back[2];
    sides[0][1][h] = back[1];
    sides[0][2][h] = back[0];
    sides[1][0][h] = top[0];
    sides[1][1][h] = top[1];
    sides[1][2][h] = top[2];
    sides[5][0][h] = front[0];
    sides[5][1][h] = front[1];
    sides[5][2][h] = front[2];
    sides[3][0][2-h] = bottom[2];
    sides[3][1][2-h] = bottom[1];
    sides[3][2][2-h] = bottom[0];
  }

  public Rubik scramble(int times) {
    for (int i = 0; i < times; i++) {
      int h = (int)(Math.random()*3);
      int turn = (int)(Math.random()*6);
      if (turn == 0)
        this.turnR(h);
      else if (turn == 1)
        this.turnL(h);
      else if (turn == 2)
        this.turnDL(h);
      else if (turn == 3)
        this.turnDR(h);
      else if (turn == 4)
        this.turnU(h);
      else
        this.turnD(h);
    }
    return this;
  }
  //solving section//
    public int solve() {
      int count = 0;
      Rubik cube = new Rubik();
      System.arraycopy(sides,0,cube.getSides(),0,3);
      Rubik copy = new Rubik();
      System.arraycopy(sides,0,copy.getSides(),0,3);
      Rubik solved = new Rubik();

      int side1 = 0;
      int largest = this.numSame(0);
      for (int i = 1; i < 6; i++) {
        if (this.numSame(i) > largest) {
          largest = this.numSame(i);
          side1 = i;
        }
      }
      int i = 1;
      while(!cube.getSides()[side1].equals(solved.getSides()[side1])) {
        int count1 = 0;
        while(!cube.getSides()[side1].equals(solved.getSides()[side1]) && count1 < 15) {
          copy.scramble(i);
          if (copy.numSame(side1) > (cube.numSame(side1))) {
            System.arraycopy(copy.getSides(),0,cube.getSides(),0,3);
            count1 += i;
          }
          else
            System.arraycopy(cube.getSides(),0,copy.getSides(),0,3);
          count1++;
          cube.print(side1);
        }
        i++;
        cube.print(side1);
        System.out.println(i);
      }


      System.out.println("Solved!");
      return count;
    }

    public char[][] findGroup(int side) {
      int[] nums = new int[6];
      for (char[] x : sides[side]) {
        for (char y : x) {
          nums[toInt(y)]++;
        }
      }
      char[][] pattern = sides[side];
      int maxCount = nums[0];
      int maxColor = 0;
      for (int i = 1; i < 6; i++) {
        if (nums[i] > maxCount) {
          maxCount = nums[i];
          maxColor = i;
        }
      }
      for (char[] x : pattern) {
        if (toInt(x[0]) != maxColor)
          x[0] = 'e';
        if (toInt(x[1]) != maxColor)
          x[1] = 'e';
        if (toInt(x[2]) != maxColor)
          x[2] = 'e';
      }
      return pattern;
    }

    public int numSame(int side) {
      int[] nums = new int[6];
      for (char[] x : sides[side]) {
        for (char y : x) {
          nums[toInt(y)]++;
        }
      }
      int maxCount = nums[0];
      for (int i = 1; i < 6; i++) {
        if (nums[i] > maxCount) {
          maxCount = nums[i];
        }
      }
      return maxCount;
    }

    public int toInt(char a) {
        switch (a) {
            case 'w':
                return 0;
            case 'g':
                return 1;
            case 'r':
                return 2;
            case 'b':
                return 3;
            case 'o':
                return 4;
            default:
                return 5;
        }
      }
}
