# BlackHoleSimulation

<p>
Simulation of the physics of a Schwarzschild black hole, or static black hole. A static black hole is a black hole that has neither electric charge nor angular momentum. Schwarzschild black holes are the simplest kind of black hole and only theorical since in reallity all things are moving at all times.
</p>

<p>
This project is based on the relativistic version of a JavaScript with p5.js implementation of the original code from the Coding Train Youtube channel 114th episode: 2D Black Hole Visualization
(<a href='https://editor.p5js.org/codingtrain/sketches/4DvaeH0Ur'>souce code</a>).
</p>

### Structures of a black hole

- **Event horizon:** the point from which no information can escape the black hole. It's radius is based on the black hole's mass with the origin on the center of the black hole (singularity) and given as follows:

```
Rs = (2 * G * m) / c^2
```
	Rs -> Radius (of Schwarzschild)
	G -> Universal gravitational constant
	m -> Mass of the black hole
	c -> Speed of light

- **Photon sphere:** the radius in which light orbits the black hole. Calculated as

```
Photon Sphere = Rs * 1.5
```

- **Accretion disk:** a disk of matter which orbits the black hole. Formulae:

```
Accretion Disk = Rs * 3
```

# Features

- Uses a simplified version of the real physics involved on the calculations.
- Simulates attraction on:
    - Asteroid
    - Planet
    - Star
    - Photons

# Source

[Coding Challenge #144 - 2D Black Hole Visualization](https://thecodingtrain.com/CodingChallenges/144-black-hole-visualization.html)

# Know more:

<p>Portuguese:</p>

[Scicast #175: Buracos Negros](https://www.deviante.com.br/podcasts/scicast/scicast-175-buracos-negros/)
[Nerdcast #670: O buraco negro é mais embaixo](https://jovemnerd.com.br/nerdcast/o-buraco-negro-e-mais-embaixo/)

### Extras:

<p>Portuguese:</p>

[Nerdcast #507: Einstein, Relatividade e Ondas Gravitacionais](https://jovemnerd.com.br/nerdcast/nerdcast-507-einstein-relatividade-e-ondas-gravitacionais/)
[Scicast #218: Relatividade](https://www.deviante.com.br/podcasts/scicast/scicast-218-relatividade/)
[Relatividade Geral (Scicast #321)](https://www.deviante.com.br/podcasts/scicast-321/)
