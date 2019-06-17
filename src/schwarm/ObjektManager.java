package schwarm;

import java.util.HashMap;

public class ObjektManager {
	   private HashMap<Integer, Insekt> insekten;
	   private static ObjektManager exemplar = new ObjektManager();

	   private ObjektManager() {
	      insekten = new HashMap<Integer, Insekt>();
	   }

	   public static ObjektManager getExemplar() {
	      return exemplar;
	   }

	   public Object clone() throws CloneNotSupportedException {
	      throw new CloneNotSupportedException("Clonen ist nicht erlaubt");
	   }
	   
	   public void registriereInsekt(Insekt obj) {
	      insekten.put(new Integer(obj.id), obj);
	   }

	   public void entferneInsekt(Insekt obj) {
	      insekten.remove(obj);
	   }
	   
	   public Insekt getInsekt(int objID) {
	      return insekten.get(new Integer(objID));
	   }
	   
	   public HashMap<Integer, Insekt> getInsektMap() {
	      return insekten;
	   }
	   
	   public int getInsektSize() {
	      return insekten.size();
	   }
}
