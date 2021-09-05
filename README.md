# clj-check

`lein check` alternative for [Clojure CLI tool](https://clojure.org/guides/deps_and_cli)

You don't have to have Leiningen to check your codebase anymore :-)

## Usage

Add the following to your `deps.edn`:

```clj
:aliases {:check {:extra-deps {athos/clj-check {:git/url "https://github.com/athos/clj-check.git"
                                                :sha "518d5a1cbfcd7c952f548e6dbfcb9a4a5faf9062
                  :exec-fn clj-check.check/check
```

If your project has its codebase under some directories other than `src` (say `src/clj` and `src/cljc`), specify them as the command line arguments as follows:

```clj
:aliases {:check {...
                  :exec-args {:source-paths ["src/clj" "src/cljc"]}}}
```


Then, run `clj-check` via the declared alias:

```
clj -X:check
```

## License

Copyright © 2018 Shogo Ohta

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
