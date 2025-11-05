package org.example.inputs.schemas.productalignment;

import org.example.bank.Annotations.*;

@KdbTable(
        description = "tracks unit weights for carriers, channels, and programs",
        name = "align_unit_weights",
        tags = {"unit", "weights", "carrier", "channel", "program"},
        type = ""
)
public class UnitWeights {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "channel")
    private String channel;

    @KdbColumn(name = "program_id")
    private Integer programId;
}
