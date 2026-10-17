CálculoCM
Calculadora interactiva con formularios dinámicos
================================================

Descripción
-----------
CálculoCM es una aplicación Android desarrollada en Kotlin con Jetpack Compose.
Permite seleccionar una calculadora desde un menú principal, introducir datos,
validarlos y mostrar el resultado junto con la fórmula y los datos utilizados.

Calculadoras disponibles
------------------------
1. Ecuación de segundo grado
   - Recibe los coeficientes a, b y c.
   - Calcula el discriminante y las raíces reales.
   - Valida que a sea diferente de cero y que el discriminante no sea negativo.

2. Volumen de un cilindro
   - Recibe el radio y la altura.
   - Calcula el volumen usando V = π · r² · h.
   - Valida que las dimensiones sean positivas y estén dentro de los límites definidos.

3. Índice de masa corporal (IMC)
   - Recibe el peso y la altura.
   - Calcula IMC = peso / altura².
   - Presenta una clasificación del resultado y valida los valores introducidos.

4. Ley de Ohm
   - Recibe el voltaje y la resistencia.
   - Calcula la corriente usando I = V / R.
   - Valida que la resistencia sea positiva y que los valores estén dentro de los límites definidos.

Funcionamiento general
----------------------
1. El usuario selecciona una de las cuatro calculadoras en el menú.
2. La pantalla muestra la información, la fórmula y únicamente los campos que
   corresponden al cálculo elegido.
3. El usuario introduce los valores solicitados.
4. Al pulsar «Calcular», la aplicación comprueba que los campos no estén vacíos,
   que los datos tengan un formato numérico válido y que sean finitos.
5. Se aplican las validaciones específicas de cada calculadora.
6. Si los datos son válidos, se realiza el cálculo y se muestra el resultado,
   incluyendo los datos utilizados. Si hay un error, se muestra un mensaje al usuario.
7. El usuario puede regresar al menú y elegir otra calculadora.

Validación de números
---------------------
Los campos se reciben inicialmente como texto. Antes de convertirlos a Double,
se comprueba su formato usando una expresión regular definida en los recursos
de texto de Android (strings.xml). Esto permite centralizar el patrón de entrada
y rechazar formatos como «.3», «-.3» o «12.» si no contienen dígitos antes y
después del punto decimal. Los números negativos válidos, como «-0.3», pueden
introducirse cuando el cálculo lo permita.

Interfaz y recursos
-------------------
- Interfaz construida con Kotlin y Jetpack Compose.
- Textos visibles centralizados en recursos de texto, no escritos directamente
  en los componentes de la interfaz.
- Recursos de idioma: español e inglés.
- Diseño visual con colores personalizados, tarjetas, iconos y fondos.
- Formulario desplazable y ajuste de espacio cuando aparece el teclado.
- Teclado numérico para facilitar la captura de datos.
- Icono personalizado de la aplicación.

Tecnologías
-----------
- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Android resources (strings.xml)

Configuración
-------------
- Paquete de la aplicación: com.example.calculocm
- API mínima requerida por el ejercicio: 28 (Android 9), según la configuración
  final del proyecto.
- Proyecto desarrollado como práctica individual.

Estructura relevante
--------------------
- MainActivity.kt: menú principal y selección de calculadora.
- CalculationScreen.kt: conecta el cálculo seleccionado con su formulario y lógica.
- CalculationForm.kt: formulario reutilizable, captura, validación y presentación
  de resultados y errores.
- CalculationConfigs.kt: configuración de cada formulario: títulos, fórmulas,
  campos, iconos y colores.
- CalculationFormConfig.kt: modelos de configuración de los campos y formularios.
- QuadraticCalculator.kt: lógica de la ecuación de segundo grado.
- CylinderCalculator.kt: cálculo del volumen del cilindro.
- BmiCalculator.kt: cálculo y clasificación del IMC.
- OhmCalculator.kt: cálculo de corriente mediante la Ley de Ohm.
- res/values/strings.xml: textos en español y recursos compartidos.
- res/values-en-rUS/strings.xml: textos en inglés.
- res/drawable/: imágenes de fórmulas, iconos y fondos usados por la interfaz.

