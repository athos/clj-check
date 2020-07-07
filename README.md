# clj-check

`lein check` alternative for [Clojure CLI tool](https://clojure.org/guides/deps_and_cli)

You don't have to have Leiningen to check your codebase anymore :-)

## Usage

Add the following to your `deps.edn`:

```clj
:aliases {:check {:extra-deps {athos/clj-check {:git/url "https://github.com/athos/clj-check.git"
                                                :sha "e5c848decdd6295a4f486a5cb39e8fc30daaf73a"}}
                  :main-opts ["-m" "clj-check.check"]}}
```

If your project has its codebase under some directories other than `src` (say `src/clj` and `src/cljc`), specify them as the command line arguments as follows:

```clj
:aliases {:check {...
                  :main-opts ["-m" "clj-check.check" "src/clj" "src/cljc"]}}
```


Then, run `clj-check` via the declared alias:

```
clj -Acheck
```

## License

Copyright © 2018 Shogo Ohta

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
