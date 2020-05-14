package logic;

public class Juego {

	private int[][] tablero;
	int conteo; //Numero de casillas que contienen un numero
	
	public Juego(int val) {
		tablero = new int[val][val];
		llenar();
		iniciarJuego();
	}
	
	public Juego(int[][] tab) {
		tablero=tab;
	}
	
	public int[][] getTablero() {
		return tablero;
	}
	
	public void jugada(int dir) {
		if (comprobarPosible(dir)) {
			switch(dir) {
			case 0:
				jugarArriba();
				break;
			case 1:
				jugarAbajo();
				break;
			case 2:
				jugarLeft();
				break;
			case 3:
				jugarRight();
				break;
			}
			iniciarJuego();
		}
	}
	
	public void simularJugada(int dir) {
		switch(dir) {
		case 0:
			jugarArriba();
			break;
		case 1:
			jugarAbajo();
			break;
		case 2:
			jugarLeft();
			break;
		case 3:
			jugarRight();
			break;
		}
	}
	
	private boolean comprobarPosible(int dir) {
		int[][] tab = new int[tablero.length][tablero.length];
		for (int i=0; i<tablero.length; i++) {
			for (int j=0; j<tablero.length; j++) {
				tab[i][j] = tablero[i][j];
			}
		}
		Juego simul = new Juego(tab);
		simul.simularJugada(dir);
		boolean ret = false;
		for (int i=0; i<tablero.length; i++) {
			for (int j=0; j<tablero.length; j++) {
				if (simul.getTablero()[i][j]!=this.tablero[i][j])
					ret=true;
			}
		}
		return ret;
	}
	
	private void jugarArriba() {
		for (int y=0; y<tablero.length; y++) {
			for (int i=1; i<tablero.length; i++) {
				for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i-1][j] == 0  ) {
						tablero [i-1][j] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
		
		for (int i=1; i<tablero.length; i++) {
			for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j] == tablero [i-1][j]) {
							tablero[i][j] = 0;
							tablero [i-1][j] = tablero [i-1][j]*2;
					}
			}
		}
		
		for (int y=0; y<tablero.length; y++) {
			for (int i=1; i<tablero.length; i++) {
				for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i-1][j] == 0  ) {
						tablero [i-1][j] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
	}
	
	private void jugarAbajo() {
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length-1; i++) {
				for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i+1][j] == 0  ) {
						tablero [i+1][j] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
		
		for (int i=tablero.length-2; i>=0; i--) {
			for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j] == tablero [i+1][j]) {
							tablero[i][j] = 0;
							tablero [i+1][j] = tablero [i+1][j]*2;
					}
			}
		}
		
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length-1; i++) {
				for (int j=0; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i+1][j] == 0  ) {
						tablero [i+1][j] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
	}
	
	private void jugarLeft() {
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length; i++) {
				for (int j=1; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i][j-1] == 0  ) {
						tablero [i][j-1] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
		
		for (int i=0; i<tablero.length; i++) {
			for (int j=1; j<tablero.length; j++) {
					if (tablero[i][j] == tablero [i][j-1]) {
							tablero[i][j] = 0;
							tablero [i][j-1] = tablero [i][j-1]*2;
					}
			}
		}
		
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length; i++) {
				for (int j=1; j<tablero.length; j++) {
					if (tablero[i][j]!=0 && tablero[i][j-1] == 0  ) {
						tablero [i][j-1] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
	}
	
	private void jugarRight() {
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length; i++) {
				for (int j=0; j<tablero.length-1; j++) {
					if (tablero[i][j]!=0 && tablero[i][j+1] == 0  ) {
						tablero [i][j+1] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
		
		for (int i=0; i<tablero.length; i++) {
			for (int j=tablero.length-2; j>=0; j--) {
					if (tablero[i][j] == tablero [i][j+1]) {
							tablero[i][j] = 0;
							tablero [i][j+1] = tablero [i][j+1]*2;
					}
			}
		}
		
		for (int y=0; y<tablero.length; y++) {
			for (int i=0; i<tablero.length; i++) {
				for (int j=0; j<tablero.length-1; j++) {
					if (tablero[i][j]!=0 && tablero[i][j+1] == 0  ) {
						tablero [i][j+1] = tablero [i][j];
						tablero [i][j]=0;
					}
					
				}
			}
		}
	}
	
	private void llenar() {
		for (int i=0; i<tablero.length; i++) {
			for (int j=0; j<tablero.length; j++) {
				tablero[i][j] = 0;
			}
		}
	}
	
	public int getConteo() {
		return this.conteo;
	}
	
	private void actualizarCuenta() {
		conteo = 0;
		for (int i=0; i<tablero.length; i++) {
			for (int h=0; h<tablero.length; h++) {
				if (tablero[i][h]!=0)
					conteo++;
			}
		}
	}
	
	public int iniciarJuego() { //Devuelve 1 si se ha llenado el tablero y acabado el juego
		actualizarCuenta();
		if (conteo >= tablero.length*tablero.length)
			return 1; //Partida perdida
		int positionInicialX = (int) (Math.random() * tablero.length);
		int positionInicialY = (int) (Math.random() * tablero.length);
		int val=2;
		if(Math.random()*10 > 6.5)
			val=4;
		
		if (tablero[positionInicialX][positionInicialY]==0)
			tablero[positionInicialX][positionInicialY] = val;	
		else 
			iniciarJuego();
		actualizarCuenta();
		return 0;
	}
	
}
