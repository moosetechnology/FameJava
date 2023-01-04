# Fame-Java


Fame is a concise meta-meta-model. This is a fork of the original Fame meta-meta-model implementation by Adrian Kuhn and Toon Verwaest.

This is an implementation of Fame in Java that supports traits concept used in the last Famix

## User

To generate the java classes of a [Moose](https:modularmoose.org) meta-model:

1. generate (in Pharo) a `.mse` file describing the meta-model.
   command: `'some-file-name.mse' asFileReference writeStreamDo: [:stream | MooseModel metamodel exportOn: stream]`
3. add the `.mse` file under the "resources" folder.
4. under "src/metamodel.codegen" create a new "target" following the template of other Codegen class.
5. execute the "target" to generate the code under the folder `gen`.

## License: GNU LESSER GENERAL PUBLIC LICENSE
