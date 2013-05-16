//
//  ConsultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ConsultVO.h"
#import "ConsultCell.h"

@interface ConsultVO()
{
@private
    NSInteger consultid;
}
@end

@implementation ConsultVO

@synthesize consultcontent = consultcontent_;
@synthesize publishtime = publishtime_;
@synthesize consultResultVO = consultResultVO_;

-(void)setConsultid:(NSInteger )kconsultid
{
    consultid = kconsultid;
    self.Id = kconsultid;
}
-(NSInteger )Consultid
{
    return consultid;
}

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return [ConsultCell calCellHeight:self];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *cellIdentifier = @"ConsultCell";
    ConsultCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:cellIdentifier owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}

@end
