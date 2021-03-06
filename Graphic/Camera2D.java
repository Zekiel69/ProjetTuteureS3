/**
 * Cette objet sert à gérer a camera dans un espace 2D.
 * Les coorodnnées de la camera peuvent être contraintes grâce à setBound(top,bot,left,right).
 * setPos(x,y) permet de recadrer la camera aux positions x,y.
 * move(x,y) applique un vecteur de déplacement de coordonnées x,y.
 * useCamera() rédéfinit les paramètres OpenGL pour utiliser les données de cette camera.
 * @author Bernelin Antoine
 */

package Graphic;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glTranslatef;


public class Camera2D {
	private float m_posX,m_posY;
	private float m_BT,m_BB,m_BL,m_BR;
	/**
	 * Instancie un objet Camera avec des paramètres par défaut: setPos(0,0), setBound(1000,-1000,-1000,1000).
	 * @author Bernelin Antoine
	 */
	public Camera2D() {
		setPos(0.0f,0.0f);
		setBound(1000.0f,-1000.0f,-1000.0f,1000.0f);
	}
	/**
	 * Modifie la camera openGL avec les données de l'instance. Cette fonction doit être appelée avant toute tentative de dessiner sur la carte graphique.
	 * @author Bernelin Antoine
	 */
	public void useCamera() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glTranslatef(-m_posX,-m_posY,0.0f);
		glMatrixMode(GL_MODELVIEW);
	}
	/**
	 * Déplace la camera selon un vecteur passé en paramètre.
     * @param x float représentant le déplacement horizontal.
     * @param y float représentant le déplacement vertical.
	 * @author Bernelin Antoine
	 */
	public void move(float x,float y) {
		if(x>0.0f)
			m_posX=min(m_posX+x,m_BR);
		else
			m_posX=max(m_posX+x,m_BL);
		if(y>0.0f)
			m_posY=min(m_posY+y,m_BT);
		else
			m_posY=max(m_posY+y,m_BB);
	}
	/**
	 * Déplace la camera aux coordonnées x,y.
     * @param x float représentant la nouvelle horizontal.
     * @param y float représentant la nouvelle vertical.
	 * @author Bernelin Antoine
	 */
	public void setPos(float x,float y) {
			m_posX=min(max(m_BL,x),m_BR);
			m_posY=min(max(m_BB,y),m_BT);
	}
	/**
	 * Définit des limites pour la position de la camera. Lors des appeles de move(x,y) et setPos(x,y), les coordonées sont automatiquement corrigées pour respécter les limites.
     * @param t float représentant la borne verticale supérieure.
     * @param b float représentant la borne verticale inférieure.
     * @param l float représentant la borne horizontale inférieure.
     * @param r float représentant la borne horizontale supérieure.
	 * @author Bernelin Antoine
	 */
	public void setBound(float t, float b,float l,float r) {
		m_BT=max(b,t);
		m_BB=min(b,t);
		m_BL=min(r,l);
		m_BR=max(r,l);
	}
}
