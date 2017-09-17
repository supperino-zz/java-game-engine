
public class Screen {
	private int WIDHT, HEIGHT;
	
	public final static int MAP_WIDTH = 64;
	public final static int MAP_WIDTH_MASK = MAP_WIDTH-1;
	public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
	public int[] colours = new int[MAP_WIDTH*MAP_WIDTH*4];
	
	public int xOffset;
	public int yOffset;
	
	public SpriteSheet sheet;
	
	public Screen(int width, int height, SpriteSheet sheet){
		HEIGHT = height;
		WIDHT = width;
		this.sheet = sheet;
		
		for(int i = 0; i < MAP_WIDTH* MAP_WIDTH; i++) {
			
			/*
			 * Esta é a matriz de cores.
			 * Aqui estamos colocando em colors
			 * 
			 * */
			colours[i*4+0] = 0xff00ff;
			colours[i*4+1] = 0x00ffff;
			colours[i*4+2] = 0xffff00;
			colours[i*4+3] = 0xffffff;
		}
	}
	
    public void renderTile(int[] pixels, int offset, int row) {
    	/*
    	 * Varre toda a matriz de pixels
    	 * 
    	 * Os deslocamentos à direita = multiplicar por 8
    	 * Multiplica-se por 8 para acessar somente de tile em tile.
    	 * 
    	 * Neste codigo, estamos utilizando uma largura de 480/8, logo, há 60 tiles 8x8 por "linha".
    	 * 
    	 * */
        for (int yTile = yOffset >>3 ; yTile <= (yOffset + HEIGHT) >> 3; yTile++) {
            int yMin = yTile * 8 - yOffset;
            int yMax = yMin + 8;
            if (yMin < 0)
                    yMin = 0;
            if (yMax > HEIGHT)
                    yMax = HEIGHT;

            for (int xTile = xOffset >> 3; xTile <= (xOffset + WIDHT) >> 3; xTile++) {
                    int xMin = xTile * 8 - xOffset;
                    int xMax = xMin + 8;
                    //verifica os limites de x
                    if (xMin < 0)
                            xMin = 0;
                    if (xMax > WIDHT)
                            xMax = WIDHT;
                    //verifica qual o indice da atual tile
                    
                    /*
                     * 	Imagine esta matriz, como uma matriz de pixels
                     * 	Aqui, cada valor de pixels tem 4 cores.
                     * 	[0, 1, 2]
                     * 	[3, 4, 5]
                     *  [6, 7, 8]
                     *  
                     *  Um exemplo com a "matriz" acima, seria o MAP de tamanho = 9. (largura 3)
                     *  Mas as cores e as tiles estão em um vetor. 
                     *  Então a MASK serve pra indicar qual "indice" é a "proxima linha"
                     *  
                     *  Na matriz exemplo, MAP_WIDTH = 3, MASK = 2. Então, a coordenada do elemento 8 é, por exemplo:
                     *  
                     *  coordX + (coordY * largura)
                     *  
                     *  o que o tileIndex faz é : Como a imagem é uma matriz, quereoms a coordenada de cada bit para um
                     *  vetor. (que é o que a gente ta fazendo: jogando uma imagem num vetor)
                     *  
                     *  tileIndex funciona pegando x + (y*largura)
                     *  ou seja, o endereço de 
                     *  0 = coordX = 0, coordY = 0 -> 0+(0*3) = 0 
                     *  1 = coordX = 1, coordY = 0 -> 1+(0*3) = 1
                     *  8 = coordX = 2, coordY = 2 -> 2+(2*3) = 8
                     *  
                     * */
                    int tileIndex = (xTile & (MAP_WIDTH_MASK)) + ((yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH);

                    for (int y = yMin; y < yMax; y++) {
                    	
                    	/*
                    	 * yMin, yMax pegam as coordenadas y de cada Tile
                    	 * xMin faz o mesmo
                    	 * 
                    	 * Dai, para cada pixel entre yMin e yMax faz-se:
                    	 * 
                    	 * sheetPixel = ele recebe o indice (no mesmo formato da tileIndex) do pixel na tilesheet(imagem)
                    	 * correspondente ao y atual
                    	 * 
                    	 * tilePixel = recebe a coordenada do pixel da tela da atual tile
                    	 * 
                    	 * */
                    	
                        int sheetPixel = ((y + yOffset) & 7) * sheet.WIDTH + ((xMin + xOffset) & 7);
                        int tilePixel = offset + xMin + y * row;
                        
                        for (int x = xMin; x < xMax; x++) {
                        	
                        	/*
                        	 * Aqui é onde tudo acontece:
                        	 * 
                        	 * Para pixel da tile:
                        	 * 		- colour recebe o endereço da tile*4 , com a cor do pixel e incrementa
                        	 * 		(se voce se lembrar, la em cima, a matriz de cores é o tamanho do mapa*4, porque tem 
                        	 * 		de haver espaço para as 4 cores ficarem salvas)
                        	 *		- Ou seja, colour esta recebendo o endereço de cada cor da tilesheet
                        	 *		- e entao pixels na posição do pixel atual recebe a cor de colours na posição da cor
                        	 * 
                        	 * */
                        
                            int colour = tileIndex * 4 + sheet.pixels[sheetPixel++];
                            pixels[tilePixel++] = colours[colour];
                    }
                }
            }
        }
    }
	
}
