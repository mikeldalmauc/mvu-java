---
tags:
  - Java
  - MVU
---

# Counter App

![App Preview](<doc-images/Pasted image 20231213164321.png>)

![MVU schema](<doc-images/Pasted image 20231213174344.png>)


---
# El Modelo

Si nos fijamos ene el modelo este tiene un objeto PropertyChangeSupport que permite lanzar eventos a listeners cuando hay cambios en las propiedades del objeto modelo. 

Esto nos servirá para notificar a la vista de que ha tiene que hacer alguna actualización.

```
public class Model {
	/*
	 * Support to add change listeners
	 */
	private PropertyChangeSupport support;
	
	public void setListener(PropertyChangeListener view) {
		support.addPropertyChangeListener(view);
	}
```

---

Por otro lado tenemos ya el modelo que constará de dos partes principales, la función de inicialización, en este caso, el constructor y los getters y setters. En este el programa el modelo es solo el numero del contador.

```
	/**
	 *  Model data should go here
	 */
	private Integer count;
	
	public Model(){
		this.support = new PropertyChangeSupport(this);
	
		/**
		 * Model initial data
		 */
		this.count = 0;
	
	}

```

Es necesario en los setters notificar al objeto support de un cambio
  
```
	/*
	 * This can be improved and be made with a tag
	 */
	public void setCount(Integer count) {
		Integer old = this.count;
		this.count = count;
		support.firePropertyChange("count", old, this.count);;
	}
```

---
# La Vista

La vista ha de implementar la siguiente función, que será llamada por el objeto support del modelo cuando se produzcan cambios, indicándonos la necesidad de actualizar la vista.

```
import lombok.Data;

@Data
public class View implements PropertyChangeListener{

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.view((Model) evt.getSource());
    }
```

---

Aparte de la función requerida para el cableado de los programas, la vista constará de una función de inicialización, en este caso el constructor y función de vista, que puede reemplazar o no la visa completa. A veces es más simple reemplazar todos los objetos por unos nuevos al actualizar la vista pero puede ser más ineficiente. 

```
	/**
	 * View
	 */
	private JFrame jframe;
	private JTextField count;
	
	public View(Model model){
	
		jframe = new JFrame("Counter");
		jframe.setSize(100, 120);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
    
```

---

La función de inicialización de la vista sigue construyendo los elementos la vista y añadiendo llamadas a la función de actualización según la acción de cada botón.

La función de actualización, dependiendo del mensaje concreto y datos enviados en caso de haberlos realizará un cambio en el modelo.

```
         // Configurar el layout
        jframe.setLayout(new BoxLayout(jframe.getContentPane(), BoxLayout.Y_AXIS));

        // Crear componentes
        count = new JTextField(1);
        count.setEditable(false);
        count.setText(model.getCount()+"");
        jframe.add("count", count);

        // Boton up
        JButton boton1 = new JButton("up");
        jframe.add("up", boton1);
        boton1.addActionListener(e -> Update.update(Msg.UP, model));

        // Boton down
        JButton boton2 = new JButton("down");
        jframe.add("down", boton2);
        boton2.addActionListener(e -> Update.update(Msg.DOWN, model));
    }
```

---

Finalmente la función de vista que se llamará tras cada cambio.

```
    /**
     * View function
     * @param model
     */

    public void view(Model model){
        count.setText(model.getCount()+"");
    }
}
```

---
# Update

Esta función se encarga de actualizar el modelo según el mensaje, no necesita estado extra a parte de la referencia al modelo y el mensaje ya que es el modelo quien notifica a la vista tras el cambio.

```
    public static void update(Msg msg, Model model){

        switch (msg) {
        
            case UP:
                model.setCount(model.getCount() + 1);
                break;

            case DOWN:
                model.setCount(model.getCount() - 1);
                break;

            default:
                break;

        }
    }
```

---

# El mensaje

En este caso el mensaje es un simple enum. Se utiliza un enum ya que los valores tienen nombres semánticos permitiendo entender mejor el código y la función de cada mensaje.

```
public enum Msg {
    UP(1),
    DOWN(2);

    private int value;

    private Msg(int value){
        this.value = value;
    }
}
```

# El programa - Main

El programa main es donde las tres piezas se juntan, en este caso sobre un programa runnable de swing. 

1. Se genera el primer modelo
2. Se genera la primera vista a partir del primer modelo.
3. Se establece la vista como listener del modelo

A partir de aquí, los cambios irán actualizando la vista automáticamente.

```
    public static void main( String[] args ){
        counterProgram();
    }  

    public static void counterProgram(){

        Model model = new Model();
        View view = new View(model);
        model.setListener(view);

        SwingUtilities.invokeLater(() -> {
            view.getJframe().setVisible(true);
            view.getJframe().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }
```

# Sub programas

Con la anterior implementación, podemos generar distintas configuraciones de vistas, programas y subprogramas, cada uno con su modelo vista y función de actualización independiente.

```
    public static void main( String[] args ){
        counterProgram();
        counterProgram();
        counterProgram();
    }
```
![Apps preview](<doc-images/Pasted image 20231213173538.png>)

![MVUs schema](<doc-images/Pasted image 20231213174824.png>)


O introducir unos programas dentro, de otros, creando una jerarquía de programas modelo-vista-actualización.

![MVUs Nested schema](<doc-images/Pasted image 20231213174947.png>)
