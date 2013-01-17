//
//  NotifierVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NotifierVO.h"
#import "NotifierCell.h"

@interface NotifierVO  ()
{
@private
    NSInteger notifierid;
}
@end

@implementation NotifierVO

@synthesize notifiercontent = notifiercontent_;
@synthesize publisher = publisher_;
@synthesize publishtime = publishtime_;

-(void)setNotifierid:(NSInteger )kid
{
    self.Id = kid;
    notifierid = kid;
}
-(NSInteger )Notifierid
{
    return notifierid;
}


-(CGFloat)heightForCell:(NSInteger)kindex
{
    return [NotifierCell calCellHeight:self];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *cellIdentifier = @"NotifierCell";
    NotifierCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:cellIdentifier owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}
@end
