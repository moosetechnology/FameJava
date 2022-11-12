# Fame-Java


Fame is a concise meta-meta-model. This is a fork of the original Fame meta-meta-model implementation by Adrian Kuhn and Toon Verwaest.

## Description

This is an implementation of Fame in Java that supports traits concept that is used in the last Fama for Moose version

## User

To generate the java classes of a [Moose](https:modularmoose.org) meta-model:

1. generate (in Pharo) a `.mse` file describing the meta-model.
1. add the `.mse` file under the "samples/ch/unibe/fame/resources" folder.
1. under "test.ch.akuhn.fame.codegen.target" create a new "target" following the template of other Codegen class.
1. execute the "target" to generate the code under the folder `gen`.

The `.mse` file may be generated in Pharo from the meta model with this command:

`'some-file-name.mse' asFileReference writeStreamDo: [:stream | MooseModel metamodel exportOn: stream]`


## License: GNU LESSER GENERAL PUBLIC LICENSE
