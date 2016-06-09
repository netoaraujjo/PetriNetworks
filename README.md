Petri Network Basic Operations
==

Input file format:

<pre>
  <code>
  p1,1;p2,0;p3,0;p4,0	-> Places separated by semicolon | 'label','quantity coin'
  t1;t2;t3				-> Transitions separated by semicolon
  p1-2-t1				-> Edges. One by line. | 'origin'-'weight'-'destiny'
  </code>
</pre>