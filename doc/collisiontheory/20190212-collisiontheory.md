This will be a brief document on the applied maths behind calculating the collisions between the puck and the paddles.

First I need to get the angle between the two colliding objects.
I then rotate the speed of the objects so it is in the direction of this angle. This makes finding the resulting speeds much easier, as the collision is now an oblique collision.
This means I just have to calculate the collision in the i direction.
There is no change of speed in the j direction.
I then have to translate the respective speeds back to the x and y axes.

We can then use Newton's Law of restitution and the principle of conservation of momentum to form simultaneous equations to solve for the resulting speeds.

NEL: (v1-v2)/(u1 -u2) = - e (where e is the co-efficient of restitution)

COM: m1u1 + m2u2 = m1v1 + m2v2